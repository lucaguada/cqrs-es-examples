package hotel.crystalcity.cqrs.api.port.egress;

import hotel.crystalcity.cqrs.api.port.Dto;

import java.util.Optional;

public interface Storage<ID, DTO extends Dto<ID>> {
  void save(DTO dto);
  Optional<DTO> findBy(ID id);
}
