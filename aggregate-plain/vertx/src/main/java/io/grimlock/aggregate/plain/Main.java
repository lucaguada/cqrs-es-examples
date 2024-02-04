package io.grimlock.aggregate.plain;

import static io.vertx.core.Vertx.vertx;

public interface Main {
  static void main(String... args) {
    vertx()
      .deployVerticle(
        new HttpServer(
          router -> router.route("/").handler(ctx -> ctx.response().end("Hello, World!"))
        )
      )
      .onSuccess(it -> System.out.println("Grimlock \uD83E\uDD96 started."))
      .onFailure(it -> System.err.println(STR."Can't start Grimlock \uD83E\uDD96: \{it.getMessage()}"));
  }
}
