package hotel.grimlock.api.model;

import hotel.grimlock.api.port.EventSource;

public interface EventTarget {
  EventTarget register(EventSource commandBus);
}
