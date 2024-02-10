package hotel.grimlock.domain.value;

import app.saintmark.api.model.Value;
import com.java.util.Raisable;

import java.time.LocalDate;
import java.util.Optional;

import static com.java.util.Raisable.*;

public record Period(LocalDate from, LocalDate to) implements Value.OfTwo<LocalDate, LocalDate> {
  public static Period of(LocalDate from, LocalDate to) {
    return from != null && to != null && from.isBefore(to)
      ? new Period(from, to)
      : raise(() -> new IllegalArgumentException(STR."Can't instantiate period from \{from} to \{to}, from-date must be before to-date and both non-null"));
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
