package app.saintmark.api.model.domain;

import app.saintmark.api.model.Event;

public interface Aggregate<ID extends Id<?>> {
  ID id();

  Event<?>[] changes();
}
