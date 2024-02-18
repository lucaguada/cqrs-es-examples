package hotel.crystalcity.cqrs.model.domain.booking.service;

import hotel.crystalcity.cqrs.acl.command.DemandAvailabilityShift;
import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import hotel.crystalcity.cqrs.api.port.ingress.service.BookingService;
import hotel.crystalcity.cqrs.model.domain.booking.handler.DemandBookingAvailability;

import java.util.Optional;

public record BookingDomain(DemandBookingAvailability handler) implements BookingService, DemandAvailabilityShift {
  @Override
  public Optional<Boolean> apply(DemandAvailabilityDto demand) {
    handler.handle(fromDto(demand));
    return Optional.of(true);
  }
}
