package hotel.grimlock.domain.model.booking.command;

import hotel.grimlock.api.model.domain.Command;
import hotel.grimlock.domain.value.Room;
import hotel.grimlock.domain.value.Guests;
import hotel.grimlock.domain.value.Period;

public record DemandAvailability(Room.Number room, Period period, Guests guests) implements Command<DemandAvailability> {}
