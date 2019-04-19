package services.impl;

import dao.ItemOrderSupplierDao;
import dao.OrderSupplierDao;
import dao.ProductDao;
import dao.impl.ItemOrderSupplierDaoImpl;
import dao.impl.OrderSupplierDaoImpl;
import dao.impl.ProductDaoImpl;
import entities.ItemOrderSupplier;
import entities.OrderSupplier;
import entities.Product;
import services.OrderSupplierService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class OrderSupplierServiceImpl extends AbstractService implements OrderSupplierService {
    private static volatile OrderSupplierService INSTANCE = null;

    private OrderSupplierDao orderSupplierDao = OrderSupplierDaoImpl.getInstance();
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ItemOrderSupplierDao itemDao = ItemOrderSupplierDaoImpl.getInstance();

    @Override
    public OrderSupplier createOrderSupplier(long userId, long productId, int quantity) {
        OrderSupplier orderSupplier = new OrderSupplier();
        try {
            startTransaction();
            orderSupplier.setUserId(userId);

            Product product = productDao.get(productId);
            if (quantity < 1) {
                quantity = 1;
            }
            orderSupplier.setTotal(product.getPrice() * quantity);
            orderSupplier = orderSupplierDao.save(orderSupplier);

            ItemOrderSupplier item = new ItemOrderSupplier(orderSupplier.getId(), productId, quantity);
            itemDao.save(item);
            commit();
            return orderSupplier;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order " + orderSupplier, e);
        }
    }

    @Override
    public OrderSupplier get(Serializable id) {
        try {
            return orderSupplierDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(OrderSupplier orderSupplier) {
        try {
            orderSupplierDao.update(orderSupplier);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + orderSupplier);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderSupplierDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<OrderSupplier> getByUserId(long userId) {
        try {
            startTransaction();
            List<OrderSupplier> orders = orderSupplierDao.getByUserId(userId);
//            for (OrderSupplier orderSupplier : orders) {
//                List<ItemOrderSupplier> items = itemDao.getByOrderSupplierId(orderSupplier.getId());
//                orderSupplier.setItems(items);
//                double sum = 0;
//                for (ItemOrderSupplier item : items) {
//                    Product product = productDao.get(item.getProductId());
//                    sum += product.getPrice() * item.getQuantity();
//                }
//                commit();
//                orderSupplier.setTotal(sum);
//            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId" + userId);
        }
    }

    public static OrderSupplierService getInstance() {
        OrderSupplierService orderSupplierService = INSTANCE;
        if (orderSupplierService == null) {
            synchronized (OrderSupplierServiceImpl.class) {
                orderSupplierService = INSTANCE;
                if (orderSupplierService == null) {
                    INSTANCE = orderSupplierService = new OrderSupplierServiceImpl();
                }
            }
        }

        return orderSupplierService;
    }
}
