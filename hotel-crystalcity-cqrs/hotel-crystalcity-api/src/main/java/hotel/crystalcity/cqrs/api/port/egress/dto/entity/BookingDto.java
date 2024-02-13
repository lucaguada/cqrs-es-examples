package hotel.crystalcity.cqrs.api.port.egress.dto.entity;

import hotel.crystalcity.cqrs.api.port.Dto;

public record BookingDto(short roomNumber, ReservationDto... reservations) implements Dto<Short> {
  @Override
  public Short id() {
    return roomNumber;
  }
}
