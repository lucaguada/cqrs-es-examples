package hotel.grimlock.adapter.repository.inmemory;

import app.saintmark.api.port.EventSource;
import app.saintmark.api.port.egress.Repository;
import hotel.grimlock.domain.model.booking.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public record InMemoryBookings(EventSource source, List<Booking> bookings, ReentrantLock reentrant) implements Repository<Booking.Id, Booking> {
  public InMemoryBookings(EventSource source) {
    this(source, new ArrayList<>(), new ReentrantLock());
  }

  @Override
  public void save(Booking aggregate) {
    reentrant.lock();
    bookings.add(aggregate);
    reentrant.unlock();

    Stream.of(aggregate.changes()).forEach(source::emit);
  }

  @Override
  public Optional<Booking> load(Booking.Id id) {
    reentrant.lock();
    for (Booking booking : bookings) {
      if (booking.id().equals(id)) {
        reentrant.unlock();
        return Optional.of(booking);
      }
    }
    return Optional.empty();
  }
}
