package hotel.grimlock.domain.model.booking.command;

import hotel.grimlock.api.model.domain.Command;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.model.room.Room;
import hotel.grimlock.domain.value.Period;

public record DemandBooking(Booking.Id bookingId, Period period, Room.Number number) implements Command<DemandBooking> {}
