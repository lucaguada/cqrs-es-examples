package hotel.crystalcity.cqrs.model.domain.booking.event;

import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.model.value.Room;

public record BookingCreated(Room.Number room) implements Event<BookingCreated> {}
