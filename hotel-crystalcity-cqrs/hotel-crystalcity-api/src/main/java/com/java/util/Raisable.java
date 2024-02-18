package com.java.util;

import java.util.List;
import java.util.function.Function;

sealed public interface Raisable<THROWABLE extends Throwable> extends StringTemplate, StringTemplate.Processor<Raisable<THROWABLE>, THROWABLE> {
  Exception<RuntimeException> Raise = new Exception<>(RuntimeException::new);

  default <T> T raise() throws THROWABLE {
    process((StringTemplate) this);
    return null;
  }

  final class Exception<THROWABLE extends Throwable> implements Raisable<THROWABLE> {
    public static final Raisable<IllegalStateException> illegalState = new Exception<>(IllegalStateException::new);
    public static final Raisable<IllegalArgumentException> illegalArgument = new Exception<>(IllegalArgumentException::new);

    private final Function<String, THROWABLE> throwing;

    Exception(Function<String, THROWABLE> throwing) {this.throwing = throwing;}


    @Override
    public List<String> fragments() {
      return null;
    }

    @Override
    public List<Object> values() {
      return null;
    }

    @Override
    public Raisable<THROWABLE> process(StringTemplate template) throws THROWABLE {
      throw throwing.apply(template.toString());
    }
  }
}
