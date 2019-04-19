package dao;

import entities.ItemOrderCustomer;

import java.sql.SQLException;
import java.util.List;

public interface ItemOrderCustomerDao extends DAO<ItemOrderCustomer>{
    List<ItemOrderCustomer> getByOrderCustomerId(long orderCustomerId) throws SQLException;
}
