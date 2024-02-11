package hotel.crystalcity.cqrs.api.port;

import hotel.crystalcity.cqrs.api.model.Event;

public interface EventSource {
  @SuppressWarnings("unchecked")
  <EVENT extends Event<EVENT>> EventSource register(Event.Handler<EVENT> handler, EVENT... events);

  void emit(Event<?>... event);
}
