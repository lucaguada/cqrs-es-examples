package hotel.crystalcity.cqrs.adapter.repository.inmemory;

import hotel.crystalcity.cqrs.api.port.EventSource;
import hotel.crystalcity.cqrs.api.port.egress.Storage;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.RoomDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

public record InMemoryRooms(Map<Short, RoomDto> rooms, ReentrantLock reentrant) implements Storage<Short, RoomDto> {
  public InMemoryRooms(EventSource source) {
    this(new HashMap<>(), new ReentrantLock());
  }

  @Override
  public void save(RoomDto dto) {
    reentrant.lock();
    rooms.put(dto.id(), dto);
    reentrant.unlock();
  }

  @Override
  public Optional<RoomDto> findBy(Short id) {
    reentrant.lock();
    try {
      return Optional.ofNullable(rooms.get(id));
    } finally {
      reentrant.unlock();
    }
  }
}
