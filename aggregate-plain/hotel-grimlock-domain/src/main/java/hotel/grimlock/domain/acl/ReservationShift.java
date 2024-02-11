package hotel.grimlock.domain.acl;

import hotel.grimlock.api.acl.Shift;
import hotel.grimlock.api.port.egress.dto.ReservationDto;
import hotel.grimlock.domain.value.Guests;
import hotel.grimlock.domain.value.Period;
import hotel.grimlock.domain.model.reservation.Reservation;

public enum ReservationShift implements Shift<Reservation, ReservationDto> {
  Default;

  @Override
  public Reservation asEntity(ReservationDto dto) {
    return new Reservation(
      Period.of(dto.from(), dto.to()),
      Reservation.Status.valueOf(dto.status()),
      Guests.of(dto.guests())
    );
  }

  @Override
  public ReservationDto asDto(Reservation reservation) {
    return new ReservationDto(
      reservation.period().from(),
      reservation.period().to(),
      reservation.status().name(),
      reservation.guests().value()
    );
  }
}
