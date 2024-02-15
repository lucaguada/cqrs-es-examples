package hotel.crystalcity.cqrs.acl;

import hotel.crystalcity.cqrs.api.port.egress.dto.entity.BookingDto;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.ReservationDto;
import hotel.crystalcity.cqrs.model.domain.booking.Booking;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.stream.Stream;

public interface BookingShift extends ReservationShift {
  default Booking fromDto(BookingDto dto) {
    return new Booking(
      Room.Number.of(dto.roomNumber()),
      Stream.of(dto.reservations()).map(this::fromDto).toArray(Reservation[]::new)
    );
  }

  default BookingDto fromEntity(Booking aggregate) {
    return new BookingDto(
      aggregate.room().value(),
      Stream.of(aggregate.reservations()).map(this::fromEntity).toArray(ReservationDto[]::new)
    );
  }
}
