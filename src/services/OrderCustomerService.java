package services;

import entities.OrderCustomer;

import java.io.Serializable;
import java.util.List;

public interface OrderCustomerService {

    OrderCustomer createOrderCustomer(long userId, long productId, int quantity);
    OrderCustomer get(Serializable id);
    void update(OrderCustomer orderCustomer);
    int delete(Serializable id);

    List<OrderCustomer> getByUserId(long userId);
}
