package app.saintmark.api.model.domain;

import java.util.Optional;

public interface Aggregates<ID extends Id<?>, AGGREGATE extends Aggregate<ID>> {
  void save(AGGREGATE aggregate);
  Optional<AGGREGATE> load(ID id);
}
