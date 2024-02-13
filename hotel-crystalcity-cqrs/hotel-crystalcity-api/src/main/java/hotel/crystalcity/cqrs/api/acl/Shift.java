package hotel.crystalcity.cqrs.api.acl;

public interface Shift<ENTITY, DTO> {
  ENTITY asEntity(DTO dto);

  DTO asDto(ENTITY entity);
}
