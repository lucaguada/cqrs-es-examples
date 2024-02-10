package hotel.grimlock.api.model.view;

import hotel.grimlock.api.port.ingress.QuerySource;

public interface QueryTarget {
  QueryTarget register(QuerySource source);
}
