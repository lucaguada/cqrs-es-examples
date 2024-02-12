package hotel.crystalcity.cqrs.model.domain.booking.handler;

import hotel.crystalcity.cqrs.model.domain.booking.Bookings;
import hotel.crystalcity.cqrs.api.model.domain.CommandTarget;
import hotel.crystalcity.cqrs.api.port.ingress.CommandSource;
import hotel.crystalcity.cqrs.model.domain.booking.Booking;
import hotel.crystalcity.cqrs.model.domain.booking.command.DemandAvailability;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;

import java.util.Optional;

import static hotel.crystalcity.cqrs.model.domain.reservation.Reservation.Status.Cancelled;
import static hotel.crystalcity.cqrs.model.domain.reservation.Reservation.Status.Completed;

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