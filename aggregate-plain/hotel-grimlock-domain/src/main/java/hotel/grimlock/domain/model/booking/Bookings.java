package hotel.grimlock.domain.model.booking;

import hotel.grimlock.api.model.domain.Aggregates;
import hotel.grimlock.api.port.EventSource;
import hotel.grimlock.api.port.egress.Repository;
import hotel.grimlock.api.port.egress.dto.BookingDto;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.RoomNumber;

import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public record Bookings(Repository<UUID, BookingDto> repository, EventSource source) implements Aggregates<Booking.Id, Booking>, Iterable<Booking> {
  @Override
  public void save(Booking booking) {
    repository.save(
      new BookingDto(
        booking.id().value(),
        booking.period().from(),
        booking.period().to(),
        booking.roomNumber().value()
      )
    );
    Stream.of(booking.changes()).forEach(source::emit);
  }

  @Override
  public Optional<Booking> load(Booking.Id id) {
    return repository
      .findBy(id.value())
      .map(dto ->
        Booking.from(
          Booking.Id.from(dto.id()),
          Period.of(dto.from(), dto.to()),
          RoomNumber.of(dto.room()))
      );
  }

  @Override
  public Iterator<Booking> iterator() {
    return repository.findAll()
      .map(dto ->
        Booking.from(
          Booking.Id.from(dto.id()),
          Period.of(dto.from(), dto.to()),
          RoomNumber.of(dto.room()))
      ).iterator();
  }
}
