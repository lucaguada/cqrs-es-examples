package hotel.grimlock.domain.model.booking.handler;

import app.saintmark.api.model.domain.CommandTarget;
import app.saintmark.api.port.ingress.CommandSource;
import hotel.grimlock.domain.model.booking.command.Prepare;

public record PrepareBooking() implements CommandTarget {
  @Override
  public CommandTarget register(CommandSource source) {
    source.register(this::prepare);
    return this;
  }

  private void prepare(Prepare prepare) {

  }
}
