package com.java.util;

import java.util.function.Supplier;

public interface ThrowableSupport {
  static  <RETURN> RETURN throwing(Supplier<? extends Throwable> throwable) {
    throw (RuntimeException) throwable.get();
  }

  default <RETURN> RETURN raise(Supplier<? extends Throwable> throwable) {
    throw (RuntimeException) throwable.get();
  }

}
