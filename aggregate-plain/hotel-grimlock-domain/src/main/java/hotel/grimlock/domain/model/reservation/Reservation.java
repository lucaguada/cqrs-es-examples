package hotel.grimlock.domain.model.reservation;

import hotel.grimlock.api.model.Value;
import hotel.grimlock.api.model.domain.Aggregate;
import hotel.grimlock.api.model.domain.Id;
import hotel.grimlock.domain.value.Guests;
import hotel.grimlock.domain.value.Period;

import static com.java.util.ThrowableSupport.throwing;
import static java.util.Arrays.binarySearch;

public record Reservation(Period period, Status status, Guests guests) implements Aggregate<> {
  public static Reservation inPending(Period period, Guests guests) {
    return new Reservation(period, Status.Pending, guests);
  }

  public record Number(Long value) implements Id<Long> {
    public static Number of(long value) {
      return value > 0
        ? new Number(value)
        : throwing(() -> new IllegalArgumentException("Can't instantiate reservationNumber, reservationNumber must be a value greater than 0"));
    }

    public static Number random() {
      return new Number((long) (Math.random() * 1000000));
    }
  }

  @Override
  public Period value1() {
    return period;
  }

  @Override
  public Status value2() {
    return status;
  }

  public enum Status {
    Pending, Confirmed, Cancelled, Completed;

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
