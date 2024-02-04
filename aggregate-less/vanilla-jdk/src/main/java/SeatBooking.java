import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import static java.util.Arrays.*;
import static java.util.stream.IntStream.range;

enum Row {Row1, Row2, Row3, Row4, Row5;
  static final int full = stream(Seat.values()).map(seat -> seat.id).reduce(0, (acc, seat) -> acc | seat);
  static {
    System.out.println(full);
  }
}

enum Seat {Seat1(1), Seat2(2), Seat3(4), Seat4(8), Seat5(16);
  private final int id;
  Seat(int id) {
    this.id = id;
  }
}

enum User {Merlin, Wart} // you know why, right? ;)

sealed interface Event<EVENT extends Event<EVENT>> {
  record SeatBooked(User user, Row row, Seat... seats) implements Event<SeatBooked> {}

  record Uncommitted<EVENT extends Event<EVENT>>(EVENT event, int version) {}

  record Committed<EVENT extends Event<EVENT>>(UUID id, EVENT event, int version, LocalDateTime storedAt) implements Event<SeatBooked> {
    Committed(EVENT event, int version) {
      this(UUID.randomUUID(), event, version, LocalDateTime.now());
    }
  }
}

sealed interface Command<COMMAND extends Command<COMMAND>> {
  record BookSeat(User user, Row row, Seat... seats) implements Command<BookSeat> {}
}

/**
 * event-store
 */
final Queue<Event.Committed<?>> eventStore = new ArrayDeque<>();

/**
 * load current events list
 *
 * @return current events list
 */
List<Event.Committed<?>> loadEvents() {return eventStore.stream().toList();}

/**
 * append an event to the event-store
 *
 * @param event event to append
 * @return appended event
 */
<EVENT extends Event<EVENT>> Event.Committed<EVENT> appendEvent(Event.Uncommitted<EVENT> event) {
  return switch (new Event.Committed<>(event.event, event.version)) {
    case Event.Committed<EVENT> committed when committed.version == eventStore.size() -> {
      eventStore.add(committed);
      yield committed;
    }
    default -> throw new IllegalStateException(STR."Can't append uncommitted event, event \{event} not consistent with version in event-store: \{eventStore.size()}");
  };
}

/**
 * invariant implementation for already booked seats
 *
 * @param events loaded committed-events
 * @param brow   booking row
 * @param seat  booking seat
 * @return true when row and seat are already booked false otherwise
 */
boolean alreadyBooked(List<Event.Committed<?>> events, Row brow, Seat seat) {
  return events.stream().anyMatch(stored -> switch (stored.event) {
    case Event.SeatBooked(_, var row, var seats) -> row == brow && binarySearch(seats, seat) >= 0;
    default -> false;
  });
}

/**
 * invariant implementation for making a straight booking seats mandatory with no holes, unless the seats in the middle are already booked
 *
 * @param events loaded committed-events
 * @param brow   booking row
 * @param bseats  booking seats
 * @return true when the middle-seat is mandatory, false otherwise
 */
boolean middleSeatMandatory(List<Event.Committed<?>> events, Row brow, Seat... bseats) {
  return events.stream()
    .filter(committed -> committed.event instanceof Event.SeatBooked(_, var row, _) && row == brow)
    .anyMatch(committed -> committed.event instanceof Event.SeatBooked(_, _, var seats) && inSequence(bseats));
}

boolean inSequence(Seat... seats) {
  sort(seats);
  return range(seats[0].ordinal(), seats[seats.length - 1].ordinal()).allMatch(index -> seats[index].ordinal() == index);
}

/**
 * commit an uncommitted event
 *
 * @param uncommitted the uncommitted event with a persistable event
 * @param <EVENT>
 * @return the committed event
 */
<EVENT extends Event<EVENT>> Event.Committed<EVENT> commitEvent(Event.Uncommitted<EVENT> uncommitted) {
  return switch (uncommitted) {
    case Event.Uncommitted<EVENT>(_, var version) when version == eventStore.size() -> appendEvent(uncommitted);
    default -> throw new IllegalStateException(STR."Can't commit event, event \{uncommitted} not consistent with version in event-store: \{eventStore.size()}");
  };
}

<EVENT extends Event<EVENT>> EVENT emitEvent(Event.Committed<EVENT> committed) {
  System.out.println(STR."Event \{committed} has been committed and emitted");
  return committed.event;
}

@SuppressWarnings("unchecked")
<EVENT extends Event<EVENT>> Event.Uncommitted<EVENT> handleCommand(List<Event.Committed<?>> events, Command<?> command) {
  return switch (command) {
    case Command.BookSeat(var user, var row, var seats) when stream(seats).allMatch(seat ->
      isNot(alreadyBooked(events, row, seat), STR."Can't book seat, command \{command} with already booked seats")
        && isNot(middleSeatMandatory(events, row, seats), STR."Can't book seat, command \{command} must book the middle seat")) ->
      new Event.Uncommitted<>((EVENT) new Event.SeatBooked(user, row, seats), events.size());

    default -> throw new IllegalArgumentException("Can't handle command");
  };
}

void main() {
  try (final var tasks = Executors.newVirtualThreadPerTaskExecutor()) {
    sneak(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Merlin, Row.Row1, Seat.Seat2)))));
    sneak(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Wart, Row.Row1, Seat.Seat1)))));
    sneak(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Merlin, Row.Row1, Seat.Seat2)))));

    var task1 = tasks.submit(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Wart, Row.Row2, Seat.Seat2)))));
    var task2 = tasks.submit(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Merlin, Row.Row2, Seat.Seat2)))));

    sneak(task1::get);
    sneak(task2::get);

    sneak(() -> emitEvent(commitEvent(this.<Event.SeatBooked>handleCommand(loadEvents(), new Command.BookSeat(User.Wart, Row.Row2, Seat.Seat4)))));
  }
}

boolean isNot(boolean condition, String otherwise) {
  if (condition) throw new IllegalArgumentException(otherwise);
  return true;
}

void sneak(Callable<Object> callable) {
  try {
    callable.call();
  } catch (ExecutionException e) {
    System.err.println(STR."\{e.getCause().getMessage()}");
  } catch (Exception e) {
    System.err.println(STR."\{e.getMessage()}");
  }
}
