package io.grimlock.aggregate.plain;

import java.util.function.Supplier;

public interface Value<OBJECT> {
  OBJECT value();

  static <OBJECT> OBJECT raise(Supplier<? extends RuntimeException> supplier) {
    throw supplier.get();
  }

  interface OfTwo<OBJECT1, OBJECT2> {
    OBJECT1 value1();
    OBJECT2 value2();
  }
}
