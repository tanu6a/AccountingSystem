package services.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import entities.Product;
import services.ProductService;
import services.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl extends AbstractService implements ProductService {
    private static volatile ProductService INSTANCE = null;

    ProductDao productDao = ProductDaoImpl.getInstance();

    @Override
    public Product getByModelAndSupplier(String model, String supplier) {
        try {
            return productDao.getByModelAndSupplier(model, supplier);
        } catch (SQLException e) {
            throw new ServiceException("Error getting by Supplier:" + supplier + " and Model:" + model);
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            startTransaction();
            List<Product> list = productDao.getAll();
            commit();
            return list;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Products");
        }
    }

    public static ProductService getInstance() {
        ProductService productService = INSTANCE;
        if (productService == null) {
            synchronized (ProductServiceImpl.class) {
                productService = INSTANCE;
                if (productService == null) {
                    INSTANCE = productService = new ProductServiceImpl();
                }
            }
        }

        return productService;
    }
}
