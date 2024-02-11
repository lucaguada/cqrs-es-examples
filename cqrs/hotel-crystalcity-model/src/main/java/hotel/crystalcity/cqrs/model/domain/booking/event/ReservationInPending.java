package hotel.crystalcity.cqrs.model.domain.booking.event;

import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.model.value.Room;
import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.model.value.Period;

public record ReservationInPending(Room.Number number, Period period, Guests guests) implements Event<ReservationInPending> {
}
