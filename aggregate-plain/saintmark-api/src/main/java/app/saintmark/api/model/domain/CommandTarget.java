package app.saintmark.api.model.domain;

import app.saintmark.api.port.ingress.CommandSource;

public interface CommandTarget {
  CommandTarget register(CommandSource source);
}
