package hotel.grimlock.domain.model.booking;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.api.model.domain.Aggregate;
import hotel.grimlock.domain.model.room.Room;
import hotel.grimlock.domain.value.Period;

import java.util.UUID;

public record Booking(Booking.Id id, Period period, Room.Number roomNumber, Status status, Event<?>... changes) implements Aggregate<Booking.Id> {
  public static Booking from(Id id, Period period, Room.Number room) {
    return new Booking(id, period, room, Status.Pending);
  }

  public boolean has(Room.Number room) {
    return this.roomNumber.equals(room);
  }

  public record Id(UUID value) implements hotel.grimlock.api.model.domain.Id<UUID> {
    public static Id from(UUID id) {
      return new Id(id);
    }

    public static Id random() {
      return new Id(UUID.randomUUID());
    }

    public static final Id ZERO = new Id(new UUID(0, 0));
  }

  public enum Status {
    Pending, Confirmed, Cancelled, Completed
  }

  public boolean overlaps(Period period) {
    return period.from().isAfter(this.period.from()) && period.from().isBefore(this.period.to()) ||
      period.to().isAfter(this.period.from()) && period.to().isBefore(this.period.to());
  }

  public boolean is(Status status) {
    return this.status.equals(status);
  }
}
