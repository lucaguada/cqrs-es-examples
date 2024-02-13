package hotel.crystalcity.cqrs.api.port.ingress.dto.command;

import hotel.crystalcity.cqrs.api.port.Dto;

import java.time.LocalDate;

public record DemandAvailabilityDto(short roomNumber, LocalDate from, LocalDate to, byte guests) implements Dto<Short> {
  @Override
  public Short id() {
    return roomNumber;
  }
}
