package dao;

import entities.ItemOrderSupplier;

import java.sql.SQLException;
import java.util.List;

public interface ItemOrderSupplierDao extends DAO<ItemOrderSupplier>{
    List<ItemOrderSupplier> getByOrderSupplierId(long orderSupplierId) throws SQLException;
}
