package hotel.crystalcity.cqrs.model.domain.booking.command;

import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.api.model.domain.Command;
import hotel.crystalcity.cqrs.model.value.Room;
import hotel.crystalcity.cqrs.model.value.Period;

public record DemandAvailability(Room.Number room, Period period, Guests guests) implements Command<DemandAvailability> {}
