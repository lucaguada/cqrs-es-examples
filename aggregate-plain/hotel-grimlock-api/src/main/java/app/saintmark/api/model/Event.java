package app.saintmark.api.model;

import java.util.function.Consumer;

public interface Event<EVENT extends Event<EVENT>> {
  interface Handler<EVENT extends Event<EVENT>> extends Consumer<EVENT> {}
}
