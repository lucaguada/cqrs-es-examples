package hotel.grimlock.api.model.domain;

import hotel.grimlock.api.model.Event;

public interface Aggregate<ID extends Id<?>> {
  ID id();

  Event<?>[] changes();
}
