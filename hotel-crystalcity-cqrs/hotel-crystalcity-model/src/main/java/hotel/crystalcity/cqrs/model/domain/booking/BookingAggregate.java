package hotel.crystalcity.cqrs.model.domain.booking;

import com.java.util.ArraySupport;
import hotel.crystalcity.cqrs.api.model.domain.entity.Booking;
import hotel.crystalcity.cqrs.model.domain.booking.event.ReservationInPending;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.stream.Stream;

public record BookingAggregate(Room.Number room, Stream<Reservation> reservations, Event<?>... changes) implements Booking, ArraySupport {
  public BookingAggregate(Room.Number room, Reservation... reservations) {
    this(room, Stream.of(reservations));
  }

  @Override
  public Room.Number id() {
    return room;
  }

  @Override
  public Booking addReservation(Reservation reservation) {
    return new BookingAggregate(
      room,
      Stream.concat(reservations, Stream.of(reservation)),
      new ReservationInPending(room, reservation.period(), reservation.guests())
    );
  }
}
