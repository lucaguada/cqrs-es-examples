package app.saintmark.api.model.domain;

import java.util.function.Consumer;

public interface Command<COMMAND extends Command<COMMAND>> {
  interface Handler<COMMAND extends Command<COMMAND>> extends Consumer<COMMAND> {}
}
