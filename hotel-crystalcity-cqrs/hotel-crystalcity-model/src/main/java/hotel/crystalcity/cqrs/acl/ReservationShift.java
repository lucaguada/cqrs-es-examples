package hotel.crystalcity.cqrs.acl;

import hotel.crystalcity.cqrs.api.acl.Shift;
import hotel.crystalcity.cqrs.api.port.egress.dto.entity.ReservationDto;
import hotel.crystalcity.cqrs.model.domain.reservation.Reservation;
import hotel.crystalcity.cqrs.model.value.Guests;
import hotel.crystalcity.cqrs.model.value.Period;

public interface ReservationShift {

  default Reservation fromDto(ReservationDto dto) {
    return new Reservation(
      Reservation.Number.of(dto.number()),
      Period.of(dto.from(), dto.to()),
      Reservation.Status.valueOf(dto.status()),
      Guests.of(dto.guests())
    );
  }

  default ReservationDto fromEntity(Reservation reservation) {
    return new ReservationDto(
      reservation.number().value(),
      reservation.period().from(),
      reservation.period().to(),
      reservation.status().name(),
      reservation.guests().value()
    );
  }
}
