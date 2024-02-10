package app.saintmark.api.model.domain;

import java.io.Serializable;
import java.util.function.Consumer;

public interface Command<COMMAND extends Command<COMMAND>> extends Serializable {
  interface Handler<COMMAND extends Command<COMMAND>> extends Consumer<COMMAND> {}
}
