package hotel.grimlock.domain.model.booking;

import app.saintmark.api.model.domain.Aggregates;
import app.saintmark.api.port.EventSource;
import app.saintmark.api.port.egress.Repository;
import app.saintmark.api.port.egress.dto.BookingDto;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.Room;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public record Bookings(Repository<UUID, BookingDto> repository, EventSource source) implements Aggregates<Booking.Id, Booking> {
  @Override
  public void save(Booking booking) {
    repository.save(
      new BookingDto(
        booking.id().value(),
        booking.period().from(),
        booking.period().to(),
        booking.room().value()
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
          Room.of(dto.room()))
      );
  }
}
