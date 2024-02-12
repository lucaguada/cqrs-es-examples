package hotel.crystalcity.cqrs.api.model.view;

import hotel.crystalcity.cqrs.api.port.ingress.QuerySource;

public interface QueryTarget {
  QueryTarget register(QuerySource source);
}
