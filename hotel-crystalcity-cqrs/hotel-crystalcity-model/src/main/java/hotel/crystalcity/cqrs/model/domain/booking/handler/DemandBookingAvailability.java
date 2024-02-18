package hotel.crystalcity.cqrs.model.domain.booking.handler;

import hotel.crystalcity.cqrs.api.model.domain.Command;
import hotel.crystalcity.cqrs.api.model.domain.service.CommandDispatcher;
import hotel.crystalcity.cqrs.model.domain.booking.Booking;
import hotel.crystalcity.cqrs.model.domain.booking.command.DemandAvailability;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;

import static com.java.util.Raisable.Exception.illegalState;

public record DemandBookingAvailability() implements Command.Service, Command.Handler<Booking, DemandAvailability> {
  @Override
  public CommandDispatcher register(CommandDispatcher source) {
    return source.register(this);
  }

  @Override
  public Booking apply(Booking booking, DemandAvailability demandAvailability) {
    return switch (booking) {
      case Booking(_, var reservations, _) when reservations.anyMatch(it -> it.overlaps(demandAvailability.period())) -> illegalState
        ."Can't demand booking for room \{demandAvailability.room()}, room already demanded for booking period \{demandAvailability.period()}"
        .raise();

      default -> booking.addReservation(Reservation.inPending(demandAvailability.period(), demandAvailability.guests()));
    };
  }
}
