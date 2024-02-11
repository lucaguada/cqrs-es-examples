package hotel.crystalcity.cqrs.api.model.view;

import java.io.Serializable;
import java.util.function.Consumer;

public interface Query<QUERY extends Query<QUERY>> extends Serializable {
  interface Handler<QUERY extends Query<QUERY>> extends Consumer<QUERY> {}
}
