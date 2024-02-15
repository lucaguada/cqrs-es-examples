package hotel.crystalcity.cqrs.model.domain.booking;

import hotel.crystalcity.cqrs.acl.BookingShift;
import hotel.crystalcity.cqrs.api.acl.Shift;
import hotel.crystalcity.cqrs.api.model.domain.Aggregates;
import hotel.crystalcity.cqrs.api.port.EventSource;
import hotel.crystalcity.cqrs.api.port.egress.Storage;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.BookingDto;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.Optional;

public record Bookings(Storage<Short, BookingDto> storage, EventSource source) implements Aggregates<Room.Number, Booking>, BookingShift {
  @Override
  public void save(Booking booking) {
    storage.save(fromEntity(booking));
    source.emit(booking.changes());
  }

  @Override
  public Optional<Booking> load(Room.Number id) {
    return storage.findBy(id.value()).map(this::fromDto);
  }
}
