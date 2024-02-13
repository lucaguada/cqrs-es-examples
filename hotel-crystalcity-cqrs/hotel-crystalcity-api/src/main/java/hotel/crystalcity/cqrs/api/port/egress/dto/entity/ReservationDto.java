package hotel.crystalcity.cqrs.api.port.egress.dto.entity;

import hotel.crystalcity.cqrs.api.port.Dto;

import java.time.LocalDate;

public record ReservationDto(long number, LocalDate from, LocalDate to, String status, byte guests) implements Dto<Long> {
  @Override
  public Long id() {
    return number;
  }
}
