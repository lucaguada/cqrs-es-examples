package hotel.crystalcity.cqrs.api.port.egress.dto;

import hotel.crystalcity.cqrs.api.port.Dto;

public record RoomDto(short number, String type) implements Dto<Short> {
  @Override
  public Short id() {
    return number;
  }
}
