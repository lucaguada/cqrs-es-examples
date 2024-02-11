package hotel.grimlock.api.port.egress.dto;

import hotel.grimlock.api.port.Dto;

public record RoomDto(short number, String type) implements Dto<Short> {
  @Override
  public Short id() {
    return number;
  }
}
