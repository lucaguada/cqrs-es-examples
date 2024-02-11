package hotel.grimlock.domain.model.booking.event;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.domain.value.Room;
import hotel.grimlock.domain.value.Period;

public record RoomLocked(Room.Number number, Period period) implements Event<RoomLocked> {}
