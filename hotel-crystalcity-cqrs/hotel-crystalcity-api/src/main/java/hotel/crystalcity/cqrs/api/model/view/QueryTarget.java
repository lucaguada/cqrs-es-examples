package hotel.crystalcity.cqrs.api.model.view;

import hotel.crystalcity.cqrs.api.model.domain.service.QuerySource;

public interface QueryTarget {
  QueryTarget register(QuerySource source);
}
