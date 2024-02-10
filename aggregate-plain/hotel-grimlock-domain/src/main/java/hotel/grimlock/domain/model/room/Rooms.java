package hotel.grimlock.domain.model.room;

import hotel.grimlock.api.model.domain.Aggregates;
import hotel.grimlock.api.port.EventSource;
import hotel.grimlock.api.port.egress.Repository;
import hotel.grimlock.api.port.egress.dto.RoomDto;

import java.util.Optional;

public record Rooms(Repository<Short, RoomDto> repository, EventSource source) implements Aggregates<Room.Number, Room> {
  @Override
  public void save(Room room) {
    repository.save(
      new RoomDto(
        room.number().value(),
        room.type().name()
      )
    );
    source.emit(room.changes());
  }

  @Override
  public Optional<Room> load(Room.Number number) {
    return repository
      .findBy(number.value())
      .map(dto ->
        new Room(
          Room.Number.of(dto.number()),
          Room.Type.valueOf(dto.type())
        )
      );
  }
}
