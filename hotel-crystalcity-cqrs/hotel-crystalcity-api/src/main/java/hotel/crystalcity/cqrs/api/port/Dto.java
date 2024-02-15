package hotel.crystalcity.cqrs.api.port;

import java.io.Serializable;

public interface Dto<ID> extends Serializable {
  ID id();
}
