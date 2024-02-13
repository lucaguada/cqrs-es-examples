package hotel.crystalcity.cqrs.api.port.ingress;

import hotel.crystalcity.cqrs.api.port.Dto;

import java.util.Optional;

public interface CommandGateway {
  <DTO extends Dto<?>> Optional<Boolean> send(String aggregate, DTO command);
}
