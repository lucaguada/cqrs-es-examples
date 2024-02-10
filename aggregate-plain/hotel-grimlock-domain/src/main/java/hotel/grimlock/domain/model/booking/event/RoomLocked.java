package hotel.grimlock.domain.model.booking.event;

import app.saintmark.api.model.Event;
import hotel.grimlock.domain.model.booking.Booking;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.value.Room;

public record RoomLocked(Booking.Id id, Period period, Room room) implements Event<RoomLocked> {}
