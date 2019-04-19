package services.impl;

import dao.ItemOrderCustomerDao;
import dao.impl.ItemOrderCustomerDaoImpl;
import entities.ItemOrderCustomer;

import services.ItemOrderCustomerService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ItemOrderCustomerServiceImpl extends AbstractService implements ItemOrderCustomerService {
    private ItemOrderCustomerDao itemDao = ItemOrderCustomerDaoImpl.getInstance();
    private static volatile ItemOrderCustomerService INSTANCE = null;

    @Override
    public ItemOrderCustomer save(ItemOrderCustomer item) {
        try {
            item = itemDao.save(item);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + item);
        }
        return item;
    }

    @Override
    public ItemOrderCustomer get(Serializable id) {
        try {
            return itemDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(ItemOrderCustomer item) {
        try {
            itemDao.update(item);
        } catch (SQLException e) {
            throw new ServiceException("Error updating Item" + item);
        }
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public List<ItemOrderCustomer> getByOrderCustomerId(long orderId) {
        try {
            return itemDao.getByOrderCustomerId(orderId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting all items");
        }
    }

    public static ItemOrderCustomerService getInstance() {
        ItemOrderCustomerService itemOrderCustomerService = INSTANCE;
        if (itemOrderCustomerService == null) {
            synchronized (ItemOrderCustomerServiceImpl.class) {
                itemOrderCustomerService = INSTANCE;
                if (itemOrderCustomerService == null) {
                    INSTANCE = itemOrderCustomerService = new ItemOrderCustomerServiceImpl();
                }
            }
        }

        return itemOrderCustomerService;
    }

}
