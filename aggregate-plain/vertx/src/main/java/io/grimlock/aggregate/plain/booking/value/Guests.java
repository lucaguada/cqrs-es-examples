package io.grimlock.aggregate.plain.booking.value;

import io.grimlock.aggregate.plain.Value;

import static io.grimlock.aggregate.plain.Value.raise;

public record Guests(Integer value) implements Value<Integer> {
  public Guests {
    assert value >= 1 && value <= 4;
  }

  public static Guests of(int value) {
    return value >= 1 && value <= 4
      ? new Guests(value)
      : raise(() -> new IllegalArgumentException("Can't instantiate Guests, value is not in its bounds"));
  }
}
