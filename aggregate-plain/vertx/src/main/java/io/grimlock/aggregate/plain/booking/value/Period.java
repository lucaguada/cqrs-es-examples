package io.grimlock.aggregate.plain.booking.value;

import io.grimlock.aggregate.plain.Value;

import java.time.LocalDate;

public record Period(LocalDate from, LocalDate to) implements Value.OfTwo<LocalDate, LocalDate> {


  @Override
  public LocalDate value1() {
    return from;
  }

  @Override
  public LocalDate value2() {
    return to;
  }
}
