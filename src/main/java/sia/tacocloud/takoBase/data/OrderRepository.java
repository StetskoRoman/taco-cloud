package sia.tacocloud.takoBase.data;

import sia.tacocloud.takoBase.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
