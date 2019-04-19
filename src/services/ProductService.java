package services;

import entities.Product;

import java.util.List;

public interface ProductService {
    Product getByModelAndSupplier(String model, String supplier);
    List<Product> getAll();
}
