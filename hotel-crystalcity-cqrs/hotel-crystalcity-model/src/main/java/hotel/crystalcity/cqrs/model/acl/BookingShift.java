package hotel.crystalcity.cqrs.model.acl;

import hotel.crystalcity.cqrs.api.acl.Shift;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.BookingDto;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.ReservationDto;
import hotel.crystalcity.cqrs.model.domain.booking.Booking;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.stream.Stream;

public enum BookingShift implements Shift<Booking, BookingDto> {
  Default(ReservationShift.Default);

  private final Shift<Reservation, ReservationDto> reservation;

  BookingShift(Shift<Reservation, ReservationDto> reservation) {this.reservation = reservation;}

  @Override
  public Booking fromDto(BookingDto dto) {
    return new Booking(
      Room.Number.of(dto.roomNumber()),
      Stream.of(dto.reservations()).map(reservation::fromDto).toArray(Reservation[]::new)
    );
  }

  @Override
  public BookingDto fromEntity(Booking aggregate) {
    return new BookingDto(
      aggregate.room().value(),
      Stream.of(aggregate.reservations()).map(reservation::fromEntity).toArray(ReservationDto[]::new)
    );
  }
}
