package hotel.crystalcity.cqrs.api.model.domain;

import hotel.crystalcity.cqrs.api.model.Event;

public interface Aggregate<ID extends Id<?>> {
  ID id();

  Event<?>[] changes();
}
