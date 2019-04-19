package services.impl;

import dao.ItemOrderCustomerDao;
import dao.OrderCustomerDao;
import dao.ProductDao;
import dao.impl.ItemOrderCustomerDaoImpl;
import dao.impl.OrderCustomerDaoImpl;
import dao.impl.ProductDaoImpl;
import entities.ItemOrderCustomer;
import entities.OrderCustomer;
import entities.Product;
import services.OrderCustomerService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class OrderCustomerServiceImpl extends AbstractService implements OrderCustomerService {
    private static volatile OrderCustomerService INSTANCE = null;

    private OrderCustomerDao orderCustomerDao = OrderCustomerDaoImpl.getInstance();
    private ProductDao productDao = ProductDaoImpl.getInstance();
    private ItemOrderCustomerDao itemDao = ItemOrderCustomerDaoImpl.getInstance();

    @Override
    public OrderCustomer createOrderCustomer(long userId, long productId, int quantity) {
        OrderCustomer orderCustomer = new OrderCustomer();
        try {
            startTransaction();
            orderCustomer.setUserId(userId);

            Product product = productDao.get(productId);
            if (quantity < 1) {
                quantity = 1;
            }
            orderCustomer.setTotal(product.getPrice() * quantity);
            orderCustomer = orderCustomerDao.save(orderCustomer);

            ItemOrderCustomer item = new ItemOrderCustomer(orderCustomer.getId(), productId, quantity);
            itemDao.save(item);
            commit();
            return orderCustomer;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error creating Order " + orderCustomer, e);
        }
    }

    @Override
    public OrderCustomer get(Serializable id) {
        try {
            return orderCustomerDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error getting Order by id" + id);
        }
    }

    @Override
    public void update(OrderCustomer orderCustomer) {
        try {
            orderCustomerDao.update(orderCustomer);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Order " + orderCustomer);
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            return orderCustomerDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting Order by id" + id);
        }
    }

    @Override
    public List<OrderCustomer> getByUserId(long userId) {
        try {
            startTransaction();
            List<OrderCustomer> orders = orderCustomerDao.getByUserId(userId);
            for (OrderCustomer orderCustomer : orders) {
                List<ItemOrderCustomer> items = itemDao.getByOrderCustomerId(orderCustomer.getId());
                orderCustomer.setItems(items);
                double sum = 0;
                for (ItemOrderCustomer item : items) {
                    Product product = productDao.get(item.getProductId());
                    sum += product.getPrice() * item.getQuantity();
                }
                commit();
                orderCustomer.setTotal(sum);
            }
            return orders;
        } catch (SQLException e) {
            rollback();
            throw new ServiceException("Error getting Orders by userId: " + userId);
        }
    }

    public static OrderCustomerService getInstance() {
        OrderCustomerService orderCustomerService = INSTANCE;
        if (orderCustomerService == null) {
            synchronized (OrderCustomerServiceImpl.class) {
                orderCustomerService = INSTANCE;
                if (orderCustomerService == null) {
                    INSTANCE = orderCustomerService = new OrderCustomerServiceImpl();
                }
            }
        }

        return orderCustomerService;
    }
}
