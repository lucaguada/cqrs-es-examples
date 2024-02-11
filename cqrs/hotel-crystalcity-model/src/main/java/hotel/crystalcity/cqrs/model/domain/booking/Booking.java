package hotel.crystalcity.cqrs.model.domain.booking;

import com.java.util.ArraySupport;
import hotel.crystalcity.cqrs.model.domain.booking.event.BookingCreated;
import hotel.crystalcity.cqrs.model.domain.booking.event.ReservationInPending;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.model.domain.booking.event.RoomLocked;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.function.Predicate;

public record Booking(Room.Number room, Reservation[] reservations, Event<?>... changes) implements Aggregate<Room.Number>, ArraySupport {
  public static Booking withReservation(Room.Number number, Reservation... reservation) {
    return new Booking(
      number,
      reservation,
      new BookingCreated(number),
      new ReservationInPending(number, reservation[0].period(), reservation[0].guests()),
      new RoomLocked(number, reservation[0].period())
    );
  }

  @Override
  public Room.Number id() {
    return room;
  }

  public Booking addReservation(Reservation reservation) {
    return new Booking(
      room,
      concat(reservation, reservations),
      new ReservationInPending(room, reservation.period(), reservation.guests()),
      new RoomLocked(room, reservation.period())
    );
  }

  public boolean contains(Predicate<Reservation> condition) {
    return stream(reservations).anyMatch(condition);
  }
}
