package dao;

import entities.OrderCustomer;

import java.sql.SQLException;
import java.util.List;

public interface OrderCustomerDao extends DAO <OrderCustomer> {
    List<OrderCustomer> getByUserId(long userId) throws SQLException;
}
