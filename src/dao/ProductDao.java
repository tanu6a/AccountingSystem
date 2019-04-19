package dao;

import java.sql.SQLException;
import java.util.List;

import entities.Product;

public interface ProductDao extends DAO<Product> {
    Product getByModelAndSupplier(String model, String supplier) throws SQLException;
    List<Product> getAll() throws SQLException;
}
