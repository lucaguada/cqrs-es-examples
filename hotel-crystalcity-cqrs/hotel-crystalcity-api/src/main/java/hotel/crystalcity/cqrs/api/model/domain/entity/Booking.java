package hotel.crystalcity.cqrs.api.model.domain.entity;

import hotel.crystalcity.cqrs.api.model.domain.Aggregate;
import hotel.crystalcity.cqrs.api.model.domain.Id;

public interface Booking<ID extends Id<?>> extends Aggregate<ID> {

  Booking<ID> addReservation(Reservation<?> reservation);
}
