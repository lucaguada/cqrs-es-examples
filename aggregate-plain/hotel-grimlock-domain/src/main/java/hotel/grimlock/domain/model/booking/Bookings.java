package hotel.grimlock.domain.model.booking;

import hotel.grimlock.api.acl.Shift;
import hotel.grimlock.api.model.domain.Aggregates;
import hotel.grimlock.api.port.EventSource;
import hotel.grimlock.api.port.egress.Storage;
import hotel.grimlock.api.port.egress.dto.BookingDto;
import hotel.grimlock.domain.value.Room;

import java.util.Optional;

public record Bookings(Storage<Short, BookingDto> storage, Shift<Booking, BookingDto> shift, EventSource source) implements Aggregates<Room.Number, Booking> {
  @Override
  public void save(Booking booking) {
    storage.save(shift.asDto(booking));
    source.emit(booking.changes());
  }

  @Override
  public Optional<Booking> load(Room.Number id) {
    return storage.findBy(id.value()).map(shift::asEntity);
  }
}
