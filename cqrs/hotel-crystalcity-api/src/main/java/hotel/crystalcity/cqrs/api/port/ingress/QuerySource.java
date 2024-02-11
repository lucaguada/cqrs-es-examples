package hotel.crystalcity.cqrs.api.port.ingress;

import hotel.crystalcity.cqrs.api.model.view.Query;

public interface QuerySource {
  @SuppressWarnings("unchecked")
  <QUERY extends Query<QUERY>> QuerySource register(Query.Handler<QUERY> handler, QUERY... queries);

  <QUERY extends Query<QUERY>> void send(QUERY query);
}
