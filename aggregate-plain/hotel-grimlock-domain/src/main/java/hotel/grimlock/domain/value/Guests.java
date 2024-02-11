package hotel.grimlock.domain.value;

import hotel.grimlock.api.model.domain.Value;

import static com.java.util.ThrowableSupport.throwing;

public record Guests(Byte value) implements Value<Byte> {
  private static final byte MAX_GUESTS = 50;

  public Guests { guard(value); }

  public static Guests of(byte value) {
    return new Guests(guard(value));
  }

  private static byte guard(byte value) {
    return value > 0 && value <= MAX_GUESTS
      ? value
      : throwing(() -> new IllegalArgumentException(STR."Can't instantiate guests, guests must be a value between 1 and \{MAX_GUESTS}"));
  }
}
