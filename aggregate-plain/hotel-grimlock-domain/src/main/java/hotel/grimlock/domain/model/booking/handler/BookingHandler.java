package hotel.grimlock.domain.model.booking.handler;

import hotel.grimlock.api.model.domain.CommandTarget;
import hotel.grimlock.api.port.ingress.CommandSource;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.model.booking.Bookings;
import hotel.grimlock.domain.model.booking.command.DemandBooking;

import static hotel.grimlock.domain.model.booking.Booking.Status.Confirmed;
import static hotel.grimlock.domain.model.booking.Booking.Status.Pending;

public record BookingHandler(Bookings bookings) implements CommandTarget {
  @Override
  public void register(CommandSource source) {
    source
      .register(this::handle);
  }

  private void handle(DemandBooking demandBooking) {
    if (bookings.load(demandBooking.bookingId()).isPresent()) throw new IllegalStateException("Can't demand booking, booking already prepared");
    for (final var booking : bookings) {
      if ((booking.is(Pending) || booking.is(Confirmed)) && booking.has(demandBooking.number()) && booking.overlaps(demandBooking.period())) {
        throw new IllegalStateException("Can't demand booking, period overlaps with another booking");
      }
    }

    bookings.save(
      Booking.from(
        demandBooking.bookingId(),
        demandBooking.period(),
        demandBooking.number()
      )
    );
  }
}
