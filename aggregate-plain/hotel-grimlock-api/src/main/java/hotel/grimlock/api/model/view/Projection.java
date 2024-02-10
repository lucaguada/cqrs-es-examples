package hotel.grimlock.api.model.view;

import hotel.grimlock.api.model.domain.Id;

public interface Projection<ENTRY extends Projection.Entry<?>> extends Iterable<ENTRY> {
  interface Entry<ID extends Id<?>> {
    ID id();
  }
}
