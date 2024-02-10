package hotel.grimlock.api.port;

import hotel.grimlock.api.model.Event;

public interface EventSource {
  @SuppressWarnings("unchecked")
  <EVENT extends Event<EVENT>> EventSource register(Event.Handler<EVENT> handler, EVENT... events);

  void emit(Event<?>... event);
}
