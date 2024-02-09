package app.saintmark.api.model;

import app.saintmark.api.port.EventSource;

public interface EventTarget {
  EventTarget register(EventSource commandBus);
}
