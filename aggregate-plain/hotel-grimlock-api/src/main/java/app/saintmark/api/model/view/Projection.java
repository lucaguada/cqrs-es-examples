package app.saintmark.api.model.view;

import app.saintmark.api.model.domain.Id;

public interface Projection<ENTRY extends Projection.Entry<?>> extends Iterable<ENTRY> {
  interface Entry<ID extends Id<?>> {
    ID id();
  }
}
