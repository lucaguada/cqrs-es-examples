package hotel.crystalcity.cqrs.api.model.domain;

import hotel.crystalcity.cqrs.api.port.ingress.CommandSource;

public interface CommandTarget {
  void bind(CommandSource source);
}
