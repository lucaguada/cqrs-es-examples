package hotel.grimlock.domain.value;

import hotel.grimlock.api.model.Value;

import java.time.LocalDate;

import static com.java.util.ThrowableSupport.*;

public record Period(LocalDate from, LocalDate to) implements Value.OfTwo<LocalDate, LocalDate> {
  public static Period of(LocalDate from, LocalDate to) {
    return from != null && to != null && from.isBefore(to)
      ? new Period(from, to)
      : throwing(() -> new IllegalArgumentException(STR."Can't instantiate period from \{from} to \{to}, from-date must be before to-date and both non-null"));
  }

  @Override
  public LocalDate value1() {
    return from;
  }

  @Override
  public LocalDate value2() {
    return to;
  }
}
