package app.saintmark.api.port.egress;

import app.saintmark.api.model.domain.Aggregate;
import app.saintmark.api.model.domain.Id;

import java.util.Optional;

public interface Repository<ID extends Id<?>, AGGREGATE extends Aggregate<ID>> {
  void save(AGGREGATE aggregate);
  Optional<AGGREGATE> load(ID id);
}
