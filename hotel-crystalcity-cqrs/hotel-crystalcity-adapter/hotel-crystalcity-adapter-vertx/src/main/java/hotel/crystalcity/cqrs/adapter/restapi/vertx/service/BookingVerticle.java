package hotel.crystalcity.cqrs.adapter.restapi.vertx.service;

import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import hotel.crystalcity.cqrs.api.port.ingress.service.BookingService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;

public final class BookingVerticle extends AbstractVerticle implements Booking.Service, Verticle {
  private final BookingService service;

  public BookingVerticle(BookingService service) {this.service = service;}

  @Override
  public void start(Promise<Void> start) {
    vertx.eventBus()
      .<DemandAvailabilityDto>consumer("demand-availability", message -> message.reply(service.apply(message.body())));
  }
}
