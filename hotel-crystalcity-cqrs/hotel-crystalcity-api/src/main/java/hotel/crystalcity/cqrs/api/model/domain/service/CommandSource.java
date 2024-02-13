package hotel.crystalcity.cqrs.api.model.domain.service;

import hotel.crystalcity.cqrs.api.model.domain.Command;

public interface CommandSource {
  @SuppressWarnings("unchecked")
  <COMMAND extends Command<COMMAND>> CommandSource register(Command.Handler<COMMAND> handler, COMMAND... commands);

  <COMMAND extends Command<COMMAND>> void send(COMMAND command);
}
