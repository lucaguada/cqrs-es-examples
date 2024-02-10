package hotel.grimlock.domain.model.booking.handler;

import app.saintmark.api.model.domain.CommandTarget;
import app.saintmark.api.port.ingress.CommandSource;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.model.booking.Bookings;
import hotel.grimlock.domain.model.booking.command.Prepare;

public record PrepareBooking(Bookings bookings) implements CommandTarget {
  @Override
  public CommandTarget register(CommandSource source) {
    source.register(this::prepare);
    return this;
  }

  private void prepare(Prepare prepare) {
    if (bookings.load(prepare.bookingId()).isPresent()) throw new IllegalStateException("Can't prepare booking, booking already prepared");

    bookings.save(
      Booking.from(
        prepare.bookingId(),
        prepare.period(),
        prepare.room()
      )
    );
  }
}
