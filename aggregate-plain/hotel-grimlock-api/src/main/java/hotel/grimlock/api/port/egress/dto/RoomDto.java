package hotel.grimlock.api.port.egress.dto;

import hotel.grimlock.api.port.Dto;

public record RoomDto(short number, String type) implements Dto<Short> {
  public static RoomDto of(short number, String type) {
    return new RoomDto(number, type);
  }

  @Override
  public Short id() {
    return number;
  }
}
