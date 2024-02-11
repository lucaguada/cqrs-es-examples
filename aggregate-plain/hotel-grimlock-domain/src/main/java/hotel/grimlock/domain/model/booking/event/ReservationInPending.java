package hotel.grimlock.domain.model.booking.event;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.domain.value.Room;
import hotel.grimlock.domain.value.Guests;
import hotel.grimlock.domain.value.Period;

public record ReservationInPending(Room.Number number, Period period, Guests guests) implements Event<ReservationInPending> {
}
