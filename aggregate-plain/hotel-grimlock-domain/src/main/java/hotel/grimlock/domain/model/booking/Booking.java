package hotel.grimlock.domain.model.booking;

import com.java.util.ArraySupport;
import hotel.grimlock.api.model.Event;
import hotel.grimlock.api.model.domain.Aggregate;
import hotel.grimlock.domain.model.booking.event.BookingCreated;
import hotel.grimlock.domain.model.booking.event.ReservationInPending;
import hotel.grimlock.domain.model.booking.event.RoomLocked;
import hotel.grimlock.domain.value.Room;
import hotel.grimlock.domain.model.reservation.Reservation;

import java.util.Arrays;
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


  public static Booking from(Room.Number room, Reservation... reservations) {
    return new Booking(room, reservations);
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
    return Arrays.stream(reservations).anyMatch(condition);
  }
}
