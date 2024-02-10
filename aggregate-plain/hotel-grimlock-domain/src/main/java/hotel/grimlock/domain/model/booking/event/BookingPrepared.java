package hotel.grimlock.domain.model.booking.event;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.domain.model.booking.Booking;

public record BookingPrepared(Booking.Id id) implements Event<BookingPrepared> {}
