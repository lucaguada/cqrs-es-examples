package app.saintmark.domain.model.booking;

import app.saintmark.api.model.Event;
import app.saintmark.api.model.domain.Aggregate;

import java.util.UUID;

public record Booking(Booking.Id id, Event<?>... changes) implements Aggregate<Booking.Id> {
  record Id(UUID value) implements app.saintmark.api.model.domain.Id<UUID> {}
}
