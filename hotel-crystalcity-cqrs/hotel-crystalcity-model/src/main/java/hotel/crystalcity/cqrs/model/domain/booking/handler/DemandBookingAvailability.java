package hotel.crystalcity.cqrs.model.domain.booking.handler;

import hotel.crystalcity.cqrs.api.model.domain.Command;
import hotel.crystalcity.cqrs.api.model.domain.service.CommandDispatcher;
import hotel.crystalcity.cqrs.model.domain.booking.BookingAggregate;
import hotel.crystalcity.cqrs.model.domain.booking.command.DemandAvailability;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;

import static com.java.util.Raisable.Exception.illegalState;

public record DemandBookingAvailability() implements Command.Service, Command.Handler<BookingAggregate, DemandAvailability> {
  @Override
  public CommandDispatcher register(CommandDispatcher source) {
    return source.register(this);
  }

  @Override
  public BookingAggregate apply(BookingAggregate booking, DemandAvailability demandAvailability) {
    return switch (booking) {
      case BookingAggregate(_, var reservations, _) when reservations.anyMatch(it -> it.overlaps(demandAvailability.period())) -> illegalState
        ."Can't demand booking for room \{demandAvailability.room()}, room already demanded for booking period \{demandAvailability.period()}"
        .raise();

      default -> booking.addReservation(Reservation.inPending(demandAvailability.period(), demandAvailability.guests()));
    };
  }
}
