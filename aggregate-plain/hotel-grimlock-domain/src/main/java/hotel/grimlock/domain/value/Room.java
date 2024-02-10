package hotel.grimlock.domain.value;

import app.saintmark.api.model.Value;

import static com.java.util.Raisable.raise;

public record Room(Short value) implements Value<Short> {
  public static Room of(short value) {
    return value >= 101 && value <= 499
      ? new Room(value)
      : raise(() -> new IllegalArgumentException("Can't instantiate room, room must be a value between 101 and 499"));
  }
}
