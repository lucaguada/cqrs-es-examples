package hotel.crystalcity.cqrs.acl.command;

import hotel.crystalcity.cqrs.api.acl.Shift;
import hotel.crystalcity.cqrs.api.port.ingress.dto.command.DemandAvailabilityDto;
import hotel.crystalcity.cqrs.model.domain.booking.command.DemandAvailability;
import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.model.value.Period;
import hotel.crystalcity.cqrs.model.value.Room;

public interface DemandAvailabilityShift extends Shift<DemandAvailability, DemandAvailabilityDto> {
  @Override
  default DemandAvailability fromDto(DemandAvailabilityDto demandAvailabilityDto) {
    return new DemandAvailability(
      Room.Number.of(demandAvailabilityDto.roomNumber()),
      Period.of(demandAvailabilityDto.from(), demandAvailabilityDto.to()),
      Guests.of(demandAvailabilityDto.guests())
    );
  }

  @Override
  default DemandAvailabilityDto fromEntity(DemandAvailability demandAvailability) {
    return new DemandAvailabilityDto(
      demandAvailability.room().value(),
      demandAvailability.period().from(),
      demandAvailability.period().to(),
      demandAvailability.guests().value()
    );
  }
}
