package app.saintmark.api.port.egress.dto;

import app.saintmark.api.port.Dto;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDto(UUID id, LocalDate from, LocalDate to, short room) implements Dto<UUID> {}
