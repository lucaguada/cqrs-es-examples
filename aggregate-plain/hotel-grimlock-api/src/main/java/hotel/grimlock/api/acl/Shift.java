package hotel.grimlock.api.acl;

import hotel.grimlock.api.port.Dto;

public interface Shift<ENTITY, DTO extends Dto<?>> {
  ENTITY asEntity(DTO dto);

  DTO asDto(ENTITY entity);
}
