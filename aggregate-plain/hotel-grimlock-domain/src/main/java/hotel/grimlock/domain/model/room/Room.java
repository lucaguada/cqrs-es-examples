package hotel.grimlock.domain.model.room;

import hotel.grimlock.api.model.Event;
import hotel.grimlock.api.model.domain.Aggregate;
import hotel.grimlock.api.model.domain.Id;

import static com.java.util.Raisable.raise;

public record Room(Room.Number number, Type type, Event<?>... changes) implements Aggregate<Room.Number> {
  @Override
  public Number id() {
    return number;
  }

  public record Number(Short value) implements Id<Short> {
    public static Number of(short value) {
      return value >= 101 && value <= 499
        ? new Number(value)
        : raise(() -> new IllegalArgumentException("Can't instantiate roomNumber, roomNumber must be a value between 101 and 499"));
    }
  }

  public enum Type {
    Single, Double, Triple, Quad
  }
}
