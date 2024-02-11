package hotel.grimlock.domain.model.booking.handler;

import hotel.grimlock.api.model.domain.CommandTarget;
import hotel.grimlock.api.port.ingress.CommandSource;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.model.booking.Bookings;
import hotel.grimlock.domain.model.booking.command.DemandAvailability;
import hotel.grimlock.domain.model.reservation.Reservation;

import java.util.Optional;

import static hotel.grimlock.domain.model.reservation.Reservation.Status.Cancelled;
import static hotel.grimlock.domain.model.reservation.Reservation.Status.Completed;

public record BookingHandler(Bookings bookings) implements CommandTarget {
  @Override
  public void bind(CommandSource source) {
    source
      .register(this::handle);
  }

  private void handle(DemandAvailability demandAvailability) {
    switch (bookings.load(demandAvailability.room())) {
      // A booking on the room has been found, check if the room is available for the period
      case Optional<Booking> it when it.isPresent() && it.get().contains(reservation -> reservation.isNot(Cancelled, Completed) && reservation.overlaps(demandAvailability.period())) ->
        throw new IllegalStateException(STR."Can't demand booking for room \{demandAvailability.room()}, room already demanded for booking period \{demandAvailability.period()}");

      // No booking on the room has been found, create a new booking
      case Optional<Booking> it when it.isEmpty() -> bookings.save(
        Booking.withReservation(
          demandAvailability.room(),
          Reservation.inPending(demandAvailability.period(), demandAvailability.guests())
        )
      );

      // A booking on the room has been found, add a new reservation to the booking
      case Optional<Booking> it when it.get() instanceof Booking booking -> bookings.save(
        booking.addReservation(Reservation.inPending(demandAvailability.period(), demandAvailability.guests()))
      );

      default -> throw new IllegalStateException(STR."Can't demand booking for room \{demandAvailability.room()}");
    }
  }
}
