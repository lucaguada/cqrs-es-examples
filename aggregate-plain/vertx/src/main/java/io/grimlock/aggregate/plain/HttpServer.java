package io.grimlock.aggregate.plain;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.ext.web.Router;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static io.vertx.ext.web.Router.router;

public class HttpServer extends AbstractVerticle implements Verticle {
  private final HttpService[] services;

  public HttpServer(HttpService... services) {this.services = services;}

  @Override
  public void start(Promise<Void> start) throws Exception {
    vertx.createHttpServer().requestHandler(
        Stream.of(services)
          .reduce(router(vertx), (router, service) -> service.apply(router), nop())
      )
      .listen(8080)
      .onSuccess(it -> start.complete())
      .onFailure(it -> start.fail(STR."Can't start http-server on port 8080: \{it.getMessage()}"));
  }

  private static BinaryOperator<Router> nop() {
    return (it, _) -> it;
  }
}
