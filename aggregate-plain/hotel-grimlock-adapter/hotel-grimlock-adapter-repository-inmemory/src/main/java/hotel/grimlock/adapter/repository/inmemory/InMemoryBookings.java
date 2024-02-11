package hotel.grimlock.adapter.repository.inmemory;

import hotel.grimlock.api.port.EventSource;
import hotel.grimlock.api.port.egress.Storage;
import hotel.grimlock.api.port.egress.dto.BookingDto;
import hotel.grimlock.api.port.egress.dto.RoomDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public record InMemoryBookings(Map<Short, BookingDto> rooms, ReentrantLock reentrant) implements Storage<Short, BookingDto> {
  public InMemoryBookings(EventSource source) {
    this(new HashMap<>(), new ReentrantLock());
  }

  @Override
  public void save(BookingDto dto) {
    reentrant.lock();
    rooms.put(dto.id(), dto);
    reentrant.unlock();
  }

  @Override
  public Optional<BookingDto> findBy(Short id) {
    reentrant.lock();
    try {
      return Optional.ofNullable(rooms.get(id));
    } finally {
      reentrant.unlock();
    }
  }
}
