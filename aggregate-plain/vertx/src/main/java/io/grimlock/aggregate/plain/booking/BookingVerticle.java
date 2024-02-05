package io.grimlock.aggregate.plain.booking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;

final class BookingVerticle  extends AbstractVerticle implements Verticle {
  private final Bookings bookings;

  BookingVerticle(Bookings bookings) {this.bookings = bookings;}

  @Override
  public void start(Promise<Void> start) {
    vertx.eventBus()
      .consumer("request-booking")
      .handler();
  }
}
