package dao;

import entities.OrderSupplier;

import java.sql.SQLException;
import java.util.List;

public interface OrderSupplierDao extends DAO <OrderSupplier> {
    List<OrderSupplier> getByUserId(long userId) throws SQLException;
}
