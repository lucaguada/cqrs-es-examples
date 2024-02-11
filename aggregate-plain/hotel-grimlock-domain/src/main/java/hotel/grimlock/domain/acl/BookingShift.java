package hotel.grimlock.domain.acl;

import hotel.grimlock.api.acl.Shift;
import hotel.grimlock.api.port.egress.dto.BookingDto;
import hotel.grimlock.api.port.egress.dto.ReservationDto;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.value.Room;
import hotel.grimlock.domain.model.reservation.Reservation;

import java.util.stream.Stream;

public enum BookingShift implements Shift<Booking, BookingDto> {
  Default(ReservationShift.Default);

  private final Shift<Reservation, ReservationDto> reservation;

  BookingShift(Shift<Reservation, ReservationDto> reservation) {this.reservation = reservation;}

  @Override
  public Booking asEntity(BookingDto dto) {
    return new Booking(
      Room.Number.of(dto.roomNumber()),
      Stream.of(dto.reservations()).map(reservation::asEntity).toArray(Reservation[]::new)
    );
  }

  @Override
  public BookingDto asDto(Booking aggregate) {
    return new BookingDto(
      aggregate.room().value(),
      Stream.of(aggregate.reservations()).map(reservation::asDto).toArray(ReservationDto[]::new)
    );
  }
}
