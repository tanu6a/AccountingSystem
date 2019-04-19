package dao.impl;

import dao.ItemOrderCustomerDao;
import dao.ItemOrderSupplierDao;
import entities.ItemOrderCustomer;
import entities.ItemOrderSupplier;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderSupplierDaoImpl extends AbstractDao implements ItemOrderSupplierDao {
    private static volatile ItemOrderSupplierDao INSTANCE = null;

    private static final String saveItemQuery = "INSERT INTO ITEM (ORDER_ID, PRODUCT_ID, QUANTITY, DISCOUNT) VALUES (?, ?, ?, ?)";
    private static final String updateItemQuery = "UPDATE ITEM SET QUANTITY=?, DISCOUNT=? WHERE ITEM_ID=?";
    private static final String getItemQuery = "SELECT * FROM ITEM WHERE ITEM_ID=?";
    private static final String getItemsByOrderQuery = "SELECT * FROM ITEM WHERE ORDER_ID = ?";
    private static final String deleteItemQuery = "DELETE FROM ITEM WHERE ITEM_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    private ItemOrderSupplierDaoImpl() {
    }

    public static ItemOrderSupplierDao getInstance() {
        ItemOrderSupplierDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemOrderSupplierDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new ItemOrderSupplierDaoImpl();
                }
            }
        }

        return itemDao;
    }

    @Override
    public ItemOrderSupplier save(ItemOrderSupplier item) throws SQLException {
        psSave = prepareStatement(saveItemQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, item.getOrderSupplierId());
        psSave.setLong(3, item.getProductId());
        psSave.setInt(4, item.getQuantity());
        psSave.setDouble(5, item.getDiscountPercent());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            item.setId(rs.getLong(1));
        }
        close(rs);
        return item;
    }

    @Override
    public ItemOrderSupplier get(Serializable id) throws SQLException {
        psGet = prepareStatement(getItemQuery);
        psGet.setLong(1, (long)id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(ItemOrderSupplier item) throws SQLException {
        psUpdate = prepareStatement(updateItemQuery);
        psUpdate.setLong(1, item.getId());
        psUpdate.setInt(4, item.getQuantity());
        psUpdate.setDouble(5, item.getDiscountPercent());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteItemQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<ItemOrderSupplier> getByOrderSupplierId(long orderId) throws SQLException {
        psGetAll = prepareStatement(getItemsByOrderQuery);
        psGetAll.setLong(1, orderId);
        psGetAll.execute();
        List<ItemOrderSupplier> list = new ArrayList<>();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);
        return list;
    }

    private ItemOrderSupplier populateEntity(ResultSet rs) throws SQLException {
        ItemOrderSupplier entity = new ItemOrderSupplier();
            entity.setId(rs.getLong(1));
            entity.setProductId(rs.getLong(3));
            entity.setQuantity(rs.getInt(4));
            entity.setDiscountPercent(rs.getDouble(5));
            entity.setOrderSupplierId(rs.getLong(2));
        return entity;
    }
}
