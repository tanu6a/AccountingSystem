package services.impl;

import dao.ItemOrderSupplierDao;
import dao.impl.ItemOrderSupplierDaoImpl;
import entities.ItemOrderSupplier;
import services.ItemOrderSupplierService;
import services.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class ItemOrderSupplierServiceImpl extends AbstractService implements ItemOrderSupplierService {
    private ItemOrderSupplierDao itemDao = ItemOrderSupplierDaoImpl.getInstance();
    private static volatile ItemOrderSupplierService INSTANCE = null;

    @Override
    public ItemOrderSupplier save(ItemOrderSupplier item) {
        try {
            item = itemDao.save(item);
        } catch (SQLException e) {
            throw new ServiceException("Error creating Item" + item);
        }
        return item;
    }

    @Override
    public ItemOrderSupplier get(Serializable id) {
        try {
            return itemDao.get(id);
        } catch (SQLException e) {
            throw new ServiceException("Error geting Item by id " + id);
        }
    }

    @Override
    public void update(ItemOrderSupplier item) {
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
    public List<ItemOrderSupplier> getByOrderSupplierId(long orderId) {
        try {
            return itemDao.getByOrderSupplierId(orderId);
        } catch (SQLException e) {
            throw new ServiceException("Error getting all items");
        }
    }

    public static ItemOrderSupplierService getInstance() {
        ItemOrderSupplierService itemOrderSupplierService = INSTANCE;
        if (itemOrderSupplierService == null) {
            synchronized (ItemOrderSupplierServiceImpl.class) {
                itemOrderSupplierService = INSTANCE;
                if (itemOrderSupplierService == null) {
                    INSTANCE = itemOrderSupplierService = new ItemOrderSupplierServiceImpl();
                }
            }
        }

        return itemOrderSupplierService;
    }
}
