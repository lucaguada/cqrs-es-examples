package hotel.grimlock.api.port.egress;

import hotel.grimlock.api.port.Dto;

import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<ID, DTO extends Dto<ID>> {
  void save(DTO dto);
  Optional<DTO> findBy(ID id);
  Stream<DTO> findAll();
}
