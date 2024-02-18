package hotel.crystalcity.cqrs;

import hotel.crystalcity.cqrs.adapter.repository.inmemory.InMemoryBookings;
import hotel.crystalcity.cqrs.adapter.restapi.vertx.restapi.HttpServerVerticle;
import hotel.crystalcity.cqrs.adapter.restapi.vertx.service.BookingVerticle;
import hotel.crystalcity.cqrs.model.domain.booking.Bookings;
import hotel.crystalcity.cqrs.model.domain.booking.handler.DemandBookingAvailability;
import hotel.crystalcity.cqrs.model.domain.booking.service.BookingDomain;
import io.vertx.core.Vertx;

interface Main {
  static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx
      .deployVerticle(new HttpServerVerticle())
      .compose(it ->
        vertx.deployVerticle(
          new BookingVerticle(
            new BookingDomain(
              new DemandBookingAvailability(
                new Bookings(
                  new InMemoryBookings()
                )
              )
            )
          )
        )
      )
      .onSuccess(it -> System.out.println("Verticles deployed"))
      .onFailure(Throwable::printStackTrace);
  }
}
