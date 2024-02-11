package hotel.grimlock.api.model.domain;

import hotel.grimlock.api.port.ingress.CommandSource;

public interface CommandTarget {
  void bind(CommandSource source);
}
