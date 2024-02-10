package app.saintmark.api.port.egress;

import app.saintmark.api.port.Dto;

import java.util.Optional;

public interface Repository<ID, DTO extends Dto<ID>> {
  void save(DTO dto);
  Optional<DTO> findBy(ID id);
}
