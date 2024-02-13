package hotel.crystalcity.cqrs.api.model.domain.service;

import hotel.crystalcity.cqrs.api.model.domain.service.CommandSource;

public interface CommandTarget {
  void bind(CommandSource source);
}
