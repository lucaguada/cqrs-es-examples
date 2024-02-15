package hotel.crystalcity.cqrs.api.port.ingress;

import hotel.crystalcity.cqrs.api.port.Dto;

import java.util.Optional;

public interface QueryGateway {
  <DTO extends Dto<?>> Dto<?>[] search(String projection, DTO command);
}
