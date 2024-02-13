package hotel.crystalcity.cqrs.api.acl;

public interface Shift<ENTITY, DTO> {
  ENTITY fromDto(DTO dto);

  DTO fromEntity(ENTITY entity);
}
