package hotel.crystalcity.cqrs.model.domain.booking;

import com.java.util.ArraySupport;
import hotel.crystalcity.cqrs.model.domain.booking.event.RoomLocked;
import hotel.crystalcity.cqrs.model.domain.booking.event.BookingCreated;
import hotel.crystalcity.cqrs.model.domain.booking.event.ReservationInPending;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.model.value.Room;

import java.util.ServiceLoader;
import java.util.function.Predicate;
import java.util.stream.Stream;

public record Booking(Room.Number room, Stream<Reservation> reservations, Event<?>... changes) implements Aggregate<Room.Number>, ArraySupport {
  public Booking(Room.Number room, Reservation... reservations) {
    this(room, Stream.of(reservations));
  }

  @Override
  public Room.Number id() {
    return room;
  }

  public Booking addReservation(Reservation reservation) {
    return new Booking(
      room,
      Stream.concat(reservations, Stream.of(reservation)),
      new ReservationInPending(room, reservation.period(), reservation.guests())
    );
  }
}
