package hotel.crystalcity.cqrs.adapter.restapi.vertx.command;

import hotel.crystalcity.cqrs.adapter.restapi.vertx.HttpService;
import hotel.crystalcity.cqrs.api.port.ingress.CommandGateway;
import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public record HttpBookingCommand(CommandGateway gateway) implements HttpService {
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
        gateway.send("booking", endpoint.body().asPojo(DemandAvailabilityDto.class)).ifPresentOrElse(
          rs -> endpoint.response().setStatusCode(202).end(),
          () -> endpoint.fail(400, new IllegalArgumentException(STR."Can't send command, command \{endpoint.pathParam("command")} unrecognized"))
        );
        break;

      default:
        endpoint.fail(400, new IllegalArgumentException(STR."Can't send command, command \{endpoint.pathParam("command")} unrecognized"));
        break;
    }
  }
}
