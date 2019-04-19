package services;

import entities.ItemOrderCustomer;

import java.io.Serializable;
import java.util.List;

public interface ItemOrderCustomerService {

    ItemOrderCustomer save(ItemOrderCustomer item);

    ItemOrderCustomer get(Serializable id);

    void update(ItemOrderCustomer item);

    int delete(Serializable id);

    List<ItemOrderCustomer> getByOrderCustomerId(long productId);
}
