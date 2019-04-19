package services;

import entities.ItemOrderCustomer;
import entities.ItemOrderSupplier;

import java.io.Serializable;
import java.util.List;

public interface ItemOrderSupplierService {

    ItemOrderSupplier save(ItemOrderSupplier item);

    ItemOrderSupplier get(Serializable id);

    void update(ItemOrderSupplier item);

    int delete(Serializable id);

    List<ItemOrderSupplier> getByOrderSupplierId(long productId);
}
