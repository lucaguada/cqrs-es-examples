package app.saintmark.api.model.view;

import app.saintmark.api.port.ingress.QuerySource;

public interface QueryTarget {
  QueryTarget register(QuerySource source);
}
