package hotel.crystalcity.cqrs.adapter.restapi.vertx.restapi.booking;

import hotel.crystalcity.cqrs.adapter.restapi.vertx.restapi.HttpService;
import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public record HttpBookingCommand(EventBus eventBus) implements HttpService {
  @Override
  public void register(Router router) {
    router
      .post("/api/booking/:command")
      .consumes("application/json")
      .produces("application/json")
      .handler(BodyHandler.create())
      .handler(this::command);
  }

  private void command(RoutingContext endpoint) {
    switch (endpoint.pathParam("command")) {
      case String command when command.equals("demand-availability"):
        eventBus.send(command, endpoint.body().asPojo(DemandAvailabilityDto.class));
        endpoint.response().setStatusCode(202).end();
        break;

      default:
        endpoint.fail(400, new IllegalArgumentException(STR."Can't send command, command \{endpoint.pathParam("command")} unrecognized"));
        break;
    }
  }
}
