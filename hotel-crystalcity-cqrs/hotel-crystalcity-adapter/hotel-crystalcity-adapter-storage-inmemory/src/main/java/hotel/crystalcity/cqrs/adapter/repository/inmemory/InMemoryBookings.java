package hotel.crystalcity.cqrs.adapter.repository.inmemory;

import hotel.crystalcity.cqrs.api.port.EventSource;
import hotel.crystalcity.cqrs.api.port.egress.Storage;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.BookingDto;

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
