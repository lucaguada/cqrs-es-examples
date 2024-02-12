package hotel.crystalcity.cqrs.model.value;

import hotel.crystalcity.cqrs.api.model.Value;
import hotel.crystalcity.cqrs.api.model.domain.Id;

import static com.java.util.ThrowableSupport.throwing;

public record Room(Room.Number number, Type type) implements Value.OfTwo<Room.Number, Room.Type> {
  @Override
  public Number value1() {
    return number;
  }

  @Override
  public Type value2() {
    return type;
  }

  public record Number(Short value) implements Id<Short> {
    public static Number of(short value) {
      return value >= 101 && value <= 499
        ? new Number(value)
        : throwing(() -> new IllegalArgumentException("Can't instantiate roomNumber, roomNumber must be a value between 101 and 499"));
    }
  }

  public enum Type {
    Single, Double, Triple, Quad
  }
}
