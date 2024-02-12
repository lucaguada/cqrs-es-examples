package hotel.crystalcity.cqrs.model.domain.reservation;

import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.api.model.Event;
import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.api.model.domain.Id;
import hotel.crystalcity.cqrs.model.value.Period;

import java.util.UUID;

import static com.java.util.ThrowableSupport.throwing;
import static java.util.Arrays.binarySearch;

public record Reservation(Number number, Period period, Status status, Guests guests, Event<?>... changes) implements Aggregate<Reservation.Number> {
  public static Reservation inPending(Period period, Guests guests) {
    return new Reservation(Number.random(), period, Status.Pending, guests);
  }

  public record Number(Long value) implements Id<Long> {
    public static Number of(long value) {
      return value > 0
        ? new Number(value)
        : throwing(() -> new IllegalArgumentException("Can't instantiate number, number must be a value greater than 0"));
    }

    public static Number random() {
      return new Number(UUID.randomUUID().timestamp());
    }
  }

  public enum Status { Pending, Confirmed, Cancelled, Completed;}

  @Override
  public Number id() {
    return number;
  }

  public boolean overlaps(Period period) {
    return period.from().isAfter(this.period.from()) && period.from().isBefore(this.period.to()) ||
      period.to().isAfter(this.period.from()) && period.to().isBefore(this.period.to());
  }

  public boolean is(Status status) {
    return this.status.equals(status);
  }

  public boolean isNot(Status... statuses) {
    return binarySearch(statuses, this.status) < 0;
  }
}
