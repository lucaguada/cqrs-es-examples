package hotel.crystalcity.cqrs.api.model;

import hotel.crystalcity.cqrs.api.port.EventSource;

public interface EventTarget {
  EventTarget register(EventSource commandBus);
}
