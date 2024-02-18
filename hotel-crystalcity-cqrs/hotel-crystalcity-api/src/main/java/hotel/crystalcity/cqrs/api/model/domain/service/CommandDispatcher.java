package hotel.crystalcity.cqrs.api.model.domain.service;

import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.api.model.domain.Command;

public interface CommandDispatcher {
  @SuppressWarnings("unchecked")
  <AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> CommandDispatcher register(Command.Handler<AGGREGATE, COMMAND> handler, COMMAND... commands);

  <COMMAND extends Command<COMMAND>> void send(COMMAND command);
}
