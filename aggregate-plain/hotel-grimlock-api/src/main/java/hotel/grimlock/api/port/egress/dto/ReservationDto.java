package hotel.grimlock.api.port.egress.dto;

import hotel.grimlock.api.port.Dto;

import java.time.LocalDate;

public record ReservationDto(LocalDate from, LocalDate to, String status, byte guests) implements Dto<String> {
  @Override
  public String id() {
    return status;
  }
}
