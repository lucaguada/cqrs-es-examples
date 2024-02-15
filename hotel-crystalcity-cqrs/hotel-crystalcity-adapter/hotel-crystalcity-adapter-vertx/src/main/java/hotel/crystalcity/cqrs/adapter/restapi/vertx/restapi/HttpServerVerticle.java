package hotel.crystalcity.cqrs.adapter.restapi.vertx.restapi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;

import static io.vertx.ext.web.Router.router;

public final class HttpServerVerticle extends AbstractVerticle implements Verticle {
  private final HttpService[] services;

  public HttpServerVerticle(HttpService... services) {this.services = services;}

  @Override
  public void start(Promise<Void> start) {
    var router = router(vertx);
    for (var httpService : services) httpService.register(router);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080)
      .onSuccess(_ -> start.complete())
      .onFailure(start::fail);
  }
}
