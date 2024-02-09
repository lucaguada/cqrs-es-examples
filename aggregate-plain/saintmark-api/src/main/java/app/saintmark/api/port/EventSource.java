package app.saintmark.api.port;

import app.saintmark.api.model.Event;

public interface EventSource {
  @SuppressWarnings("unchecked")
  <EVENT extends Event<EVENT>> EventSource register(Event.Handler<EVENT> handler, EVENT... events);

  <EVENT extends Event<EVENT>> void send(EVENT event);
}
