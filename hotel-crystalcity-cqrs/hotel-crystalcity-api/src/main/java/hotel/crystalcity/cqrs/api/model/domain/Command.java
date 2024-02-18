package hotel.crystalcity.cqrs.api.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;

public interface Command<COMMAND extends Command<COMMAND>> extends Serializable {
  @FunctionalInterface
  interface Handler<AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> extends BiFunction<AGGREGATE, COMMAND, AGGREGATE> {}

  interface Dispatcher {
    <AGGREGATE_DESCRIPTOR extends Enum<AGGREGATE_DESCRIPTOR>, COMMAND extends Command<COMMAND>> boolean dispatch(AGGREGATE_DESCRIPTOR aggregate, COMMAND command);
  }
}
