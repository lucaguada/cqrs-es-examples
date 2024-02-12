package com.java.util;

import java.util.Arrays;
import java.util.stream.Stream;

public interface ArraySupport {
  @SuppressWarnings({"unchecked"})
  default <ELEMENT> ELEMENT[] concat(ELEMENT element, ELEMENT... toElements) {
    var copied = Arrays.copyOf(toElements, toElements.length + 1);
    copied[toElements.length] = element;
    return copied;
  }

  @SuppressWarnings("unchecked")
  default <ELEMENT> Stream<ELEMENT> stream(ELEMENT... elements) {
    return Arrays.stream(elements);
  }
}
