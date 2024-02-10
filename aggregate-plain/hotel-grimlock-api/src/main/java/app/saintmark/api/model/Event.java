package app.saintmark.api.model;

import java.io.Serializable;
import java.util.function.Consumer;

public interface Event<EVENT extends Event<EVENT>> extends Serializable {
  interface Handler<EVENT extends Event<EVENT>> extends Consumer<EVENT> {}
}
