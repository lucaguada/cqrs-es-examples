package hotel.grimlock.domain.model.booking.event;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.RoomNumber;

public record RoomLocked(Booking.Id id, Period period, RoomNumber roomNumber) implements Event<RoomLocked> {}
