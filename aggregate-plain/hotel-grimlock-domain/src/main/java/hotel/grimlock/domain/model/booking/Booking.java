package hotel.grimlock.domain.model.booking;

import app.saintmark.api.model.Event;
import app.saintmark.api.model.domain.Aggregate;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.Room;

import java.util.UUID;

public record Booking(Booking.Id id, Period period, Room room, Event<?>... changes) implements Aggregate<Booking.Id> {
  public static Booking from(Id id, Period period, Room room) {
    return new Booking(id, period, room);
  }

  public record Id(UUID value) implements app.saintmark.api.model.domain.Id<UUID> {
    public static Id from(UUID id) {
      return new Id(id);
    }

    public static Id random() {
      return new Id(UUID.randomUUID());
    }

    public static final Id ZERO = new Id(new UUID(0, 0));
  }
}
