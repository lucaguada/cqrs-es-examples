package hotel.crystalcity.cqrs.api.model.domain.service;

public interface CommandTarget {
  void bind(CommandDispatcher source);
}
