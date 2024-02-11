package hotel.crystalcity.cqrs.api.acl;

import hotel.crystalcity.cqrs.api.port.Dto;

public interface Shift<ENTITY, DTO extends Dto<?>> {
  ENTITY asEntity(DTO dto);

  DTO asDto(ENTITY entity);
}
