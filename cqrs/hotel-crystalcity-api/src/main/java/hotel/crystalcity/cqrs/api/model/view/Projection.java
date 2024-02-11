package hotel.crystalcity.cqrs.api.model.view;

import hotel.crystalcity.cqrs.api.model.domain.Id;

public interface Projection<ENTRY extends Projection.Entry<?>> extends Iterable<ENTRY> {
  interface Entry<ID extends Id<?>> {
    ID id();
  }
}
