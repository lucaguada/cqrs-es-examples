package hotel.grimlock.domain.model.booking.event;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.domain.value.Room;

public record BookingCreated(Room.Number room) implements Event<BookingCreated> {}
