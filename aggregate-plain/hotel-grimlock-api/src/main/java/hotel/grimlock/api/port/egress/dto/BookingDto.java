package hotel.grimlock.api.port.egress.dto;

import hotel.grimlock.api.port.Dto;

public record BookingDto(short roomNumber, ReservationDto... reservations) implements Dto<Short> {
  @Override
  public Short id() {
    return roomNumber;
  }
}
