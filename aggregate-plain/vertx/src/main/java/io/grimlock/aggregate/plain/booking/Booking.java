package io.grimlock.aggregate.plain.booking;

import io.grimlock.aggregate.plain.Event;

import java.util.UUID;

public record Booking(UUID id, Event<?>... events) {}
