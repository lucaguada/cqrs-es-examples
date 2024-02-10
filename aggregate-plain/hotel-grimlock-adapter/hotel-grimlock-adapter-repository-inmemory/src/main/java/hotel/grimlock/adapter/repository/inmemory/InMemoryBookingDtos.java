package hotel.grimlock.adapter.repository.inmemory;

import app.saintmark.api.port.EventSource;
import app.saintmark.api.port.egress.Repository;
import app.saintmark.api.port.egress.dto.BookingDto;
import hotel.grimlock.domain.model.booking.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public record InMemoryBookingDtos(List<BookingDto> bookings, ReentrantLock reentrant) implements Repository<UUID, BookingDto> {
  public InMemoryBookingDtos(EventSource source) {
    this(new ArrayList<>(), new ReentrantLock());
  }

  @Override
  public void save(BookingDto dto) {
    reentrant.lock();
    bookings.add(dto);
    reentrant.unlock();
  }

  @Override
  public Optional<BookingDto> findBy(UUID id) {
    reentrant.lock();
    for (BookingDto booking : bookings) {
      if (booking.id().equals(id)) {
        reentrant.unlock();
        return Optional.of(booking);
      }
    }
    return Optional.empty();
  }
}