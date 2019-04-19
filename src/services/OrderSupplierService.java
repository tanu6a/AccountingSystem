package services;

import entities.OrderSupplier;

import java.io.Serializable;
import java.util.List;

public interface OrderSupplierService {

    OrderSupplier createOrderSupplier(long userId, long productId, int quantity);
    OrderSupplier get(Serializable id);
    void update(OrderSupplier orderSupplier);
    int delete(Serializable id);

    List<OrderSupplier> getByUserId(long userId);
}
