package hotel.grimlock.api.model;

import java.util.function.Supplier;

public interface Value<OBJECT> extends Supplier<OBJECT> {
  OBJECT value();

  @Override
  default OBJECT get() {
    return value();
  }

  interface OfTwo<OBJECT1, OBJECT2> {
    OBJECT1 value1();
    OBJECT2 value2();
  }
}
