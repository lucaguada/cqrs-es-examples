package hotel.grimlock.api.port.egress.dto;

import hotel.grimlock.api.port.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDto(UUID id, LocalDate from, LocalDate to, short room) implements Dto<UUID> {}
