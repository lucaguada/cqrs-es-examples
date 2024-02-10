package com.java.util;

import java.util.function.Supplier;

public interface Raisable {
  static  <T> T raise(Supplier<? extends Throwable> throwable) {
    throw (RuntimeException) throwable.get();
  }
}
