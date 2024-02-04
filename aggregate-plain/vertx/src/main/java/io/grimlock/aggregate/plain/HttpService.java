package io.grimlock.aggregate.plain;

import io.vertx.ext.web.Router;

import java.util.function.UnaryOperator;

public interface HttpService extends UnaryOperator<Router> {
  @Override
  default Router apply(Router router) {
    register(router);
    return router;
  }

  void register(Router router);
}
