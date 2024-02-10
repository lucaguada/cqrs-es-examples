package hotel.grimlock.domain.model.booking.command;

import app.saintmark.api.model.domain.Command;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.Room;

public record Prepare(Booking.Id bookingId, Period period, Room room) implements Command<Prepare> {}
