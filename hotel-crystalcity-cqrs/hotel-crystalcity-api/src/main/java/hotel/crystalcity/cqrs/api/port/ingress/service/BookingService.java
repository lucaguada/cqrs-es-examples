package hotel.crystalcity.cqrs.api.port.ingress.service;

import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;

import java.util.Optional;

public interface BookingService {
  Optional<Boolean> apply(DemandAvailabilityDto demand);
}
