package dao.impl;

import dao.OrderSupplierDao;
import entities.OrderSupplier;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderSupplierDaoImpl extends AbstractDao implements OrderSupplierDao {
    private static volatile OrderSupplierDao INSTANCE = null;

    private static final String saveQuery = "INSERT INTO `ORDER_SUPPLIER` (USER_ID, TOTAL, DATE) VALUES (?, ?, now())";
    private static final String updateQuery = "UPDATE `ORDER_SUPPLIER` SET TOTAL=? WHERE USER_ID=?";
    private static final String getQuery = "SELECT IDORDER_SUPPLIER, USER_ID FROM `ORDER_SUPPLIER` WHERE USER_ID=?";
    private static final String getAllByUserQuery = "SELECT IDORDER_SUPPLIER, USER_ID FROM `ORDER_SUPPLIER` WHERE USER_ID = ? ORDER BY USER_ID DESC";
    private static final String deleteQuery = "DELETE FROM `ORDER_SUPPLIER` WHERE USER_ID=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetAllByUserId;
    private PreparedStatement psDelete;

    @Override
    public OrderSupplier save(OrderSupplier orderSupplier) throws SQLException {
        psSave = prepareStatement(saveQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setLong(1, orderSupplier.getUserId());
        psSave.setDouble(2, orderSupplier.getTotal());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            orderSupplier.setId(rs.getLong(1));
        }
        close(rs);
        return orderSupplier;
    }

    @Override
    public OrderSupplier get(Serializable id) throws SQLException {
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
    public void update(OrderSupplier orderSupplier) throws SQLException {
        psUpdate = prepareStatement(updateQuery);
        psUpdate.setLong(1, orderSupplier.getId());
        psUpdate.setDouble(9, orderSupplier.getTotal());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = prepareStatement(deleteQuery);
        psDelete.setLong(1, (long)id);
        return psDelete.executeUpdate();
    }

    @Override
    public List<OrderSupplier> getByUserId(long userId) throws SQLException {
        psGetAllByUserId = prepareStatement(getAllByUserQuery);
        psGetAllByUserId.setLong(1, userId);
        psGetAllByUserId.execute();
        ResultSet rs = psGetAllByUserId.getResultSet();
        List<OrderSupplier> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populateEntity(rs));
        }
        close(rs);

        return list;
    }

    private OrderSupplier populateEntity(ResultSet rs) throws SQLException {
        OrderSupplier entity = new OrderSupplier();
            entity.setId(rs.getLong(1));
            entity.setUserId(rs.getLong(2));
        return entity;
    }

    public static OrderSupplierDao getInstance() {
        OrderSupplierDao itemDao = INSTANCE;
        if (itemDao == null) {
            synchronized (ItemOrderSupplierDaoImpl.class) {
                itemDao = INSTANCE;
                if (itemDao == null) {
                    INSTANCE = itemDao = new OrderSupplierDaoImpl();
                }
            }
        }

        return itemDao;
    }
}
