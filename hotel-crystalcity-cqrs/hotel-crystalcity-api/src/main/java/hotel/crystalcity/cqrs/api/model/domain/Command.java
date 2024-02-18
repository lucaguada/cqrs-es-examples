package hotel.crystalcity.cqrs.api.model.domain;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;

public interface Command<COMMAND extends Command<COMMAND>> extends Serializable {
  @FunctionalInterface
  interface Handler<AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> extends BiFunction<AGGREGATE, COMMAND, AGGREGATE> {}

  interface Service {
    Dispatcher register(Dispatcher source);
  }

  sealed interface Dispatcher {
    @SuppressWarnings("unchecked")
    <AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> Dispatcher target(Command.Handler<AGGREGATE, COMMAND> handler, COMMAND... commands);

    <COMMAND extends Command<COMMAND>> void dispatch(COMMAND command);

    record Async(ExecutorService threads, Command.Handler<? extends Aggregate<? extends Id<?>>, ? extends Command<?>>... handlers) implements Dispatcher {
      @Override
      public <AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> Dispatcher target(Command.Handler<AGGREGATE, COMMAND> handler, COMMAND... commands) {
        return null;
      }

      @Override
      public <COMMAND extends Command<COMMAND>> void dispatch(COMMAND command) {
      }
    }
  }
}
