package app.saintmark.api.model.domain;

import java.util.function.Supplier;

public interface Value<OBJECT> extends Supplier<OBJECT> {
  OBJECT value();

  @Override
  default OBJECT get() { return value(); }
}
