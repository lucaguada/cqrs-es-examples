package io.grimlock.aggregate.plain.booking;

public sealed interface Bookings {

  static Bookings inMemory() {
    return new InMemory();
  }

  void store(Booking booking);


  final class InMemory implements Bookings {
    private final Booking[] bookings;

    public InMemory(Booking... bookings) {this.bookings = bookings;}
  }
}
