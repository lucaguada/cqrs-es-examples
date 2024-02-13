package hotel.crystalcity.cqrs.model.acl.command;

import hotel.crystalcity.cqrs.api.acl.Shift;
import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import hotel.crystalcity.cqrs.model.domain.booking.command.DemandAvailability;
import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.model.value.Period;
import hotel.crystalcity.cqrs.model.value.Room;

public enum DemandAvailabilityShift implements Shift<DemandAvailability, DemandAvailabilityDto> {
  Default;

  @Override
  public DemandAvailability fromDto(DemandAvailabilityDto demandAvailabilityDto) {
    return new DemandAvailability(
      Room.Number.of(demandAvailabilityDto.roomNumber()),
      Period.of(demandAvailabilityDto.from(), demandAvailabilityDto.to()),
      Guests.of(demandAvailabilityDto.guests())
    );
  }

  @Override
  public DemandAvailabilityDto fromEntity(DemandAvailability demandAvailability) {
    return new DemandAvailabilityDto(
      demandAvailability.room().value(),
      demandAvailability.period().from(),
      demandAvailability.period().to(),
      demandAvailability.guests().value()
    );
  }
}
