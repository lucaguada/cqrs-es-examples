package app.saintmark.api.model.view;

import java.util.function.Consumer;

public interface Query<QUERY extends Query<QUERY>> {
  interface Handler<QUERY extends Query<QUERY>> extends Consumer<QUERY> {}
}
