package dao.impl;

import dao.OrderCustomerDao;
import dao.OrderSupplierDao;
import entities.OrderCustomer;
import entities.OrderSupplier;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderCustomerDaoImpl extends AbstractDao implements OrderCustomerDao {
    private static volatile OrderCustomerDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ORDER_CUSTOMER` (USER_ID, TOTAL, DATE) VALUES (?, ?, now())";
    private static final String updateQuery = "UPDATE `ORDER_CUSTOMER` SET TOTAL=? WHERE USER_ID=?";
    private static final String getQuery = "SELECT ID, USER_ID FROM `ORDER_CUSTOMER` WHERE USER_ID=?";
    private static final String getAllByUserQuery = "SELECT idorder_customer, USER_ID FROM `ORDER_CUSTOMER` WHERE USER_ID = ? ORDER BY USER_ID DESC";
    private static final String deleteQuery = "DELETE FROM `ORDER_CUSTOMER` WHERE USER_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAllByUserId;
    private PreparedStatement psDelete;

    @Override
    public OrderCustomer save(OrderCustomer orderCustomer) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(2, orderCustomer.getUserId());
        psSave.setDouble(8, orderCustomer.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            orderCustomer.setId(rs.getLong(1));
        }
        close(rs);
        return orderCustomer;
    }

    @Override
    public OrderCustomer get(Serializable id) throws SQLException {
        psGet = prepareStatement(getQuery);
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
    public void update(OrderCustomer orderCustomer) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, orderCustomer.getId());
        psUpdate.setDouble(2, orderCustomer.getTotal());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<OrderCustomer> getByUserId(long userId) throws SQLException {
        psGetAllByUserId = prepareStatement(getAllByUserQuery);
        psGetAllByUserId.setLong(1, userId);
        psGetAllByUserId.execute();
        ResultSet rs = psGetAllByUserId.getResultSet();
        List<OrderCustomer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);

        return list;
    }

    private OrderCustomer populateEntity(ResultSet rs) throws SQLException {
        OrderCustomer entity = new OrderCustomer();
            entity.setId(rs.getLong(1));
            entity.setUserId(rs.getLong(2));
        return entity;
    }

    public static OrderCustomerDao getInstance() {
        OrderCustomerDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemOrderCustomerDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new OrderCustomerDaoImpl();
                }
            }
        }

        return itemDao;
    }
}
