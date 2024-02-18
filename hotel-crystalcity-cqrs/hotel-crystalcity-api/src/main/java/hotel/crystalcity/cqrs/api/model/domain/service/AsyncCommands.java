package hotel.crystalcity.cqrs.api.model.domain.service;

import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.api.model.domain.Command;

public record AsyncCommands() implements Command.Dispatcher {
  @Override
  public <AGGREGATE extends Aggregate<?>, COMMAND extends Command<COMMAND>> Command.Dispatcher target(Command.Handler<AGGREGATE, COMMAND> handler, COMMAND... commands) {
    return null;
  }

  @Override
  public <COMMAND extends Command<COMMAND>> void dispatch(COMMAND command) {

  }
}
