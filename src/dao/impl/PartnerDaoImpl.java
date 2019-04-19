package dao.impl;

import dao.PartnerDao;
import entities.*;
import services.ServiceException;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartnerDaoImpl extends AbstractDao implements PartnerDao {
    private static volatile PartnerDao INSTANCE = null;

    private static final String savePartnerQuery = "INSERT INTO PARTNER (NAME, FULL_NAME, TYPE_PARTNER) VALUES (?, ?, ?)";
    private static final String updatePartnerQuery = "UPDATE PARTNER SET NAME=?, FULL_NAME=?, TYPE_PARTNER=? WHERE TIN=?";
    private static final String getPartnerQuery = "SELECT * FROM PARTNER WHERE TIN=?";
    private static final String getAllPartnerQuery = "SELECT * FROM PARTNER";
    private static final String getByTinQuery = "SELECT * FROM PARTNER WHERE TIN=?";
    private static final String deletePartnerQuery = "DELETE FROM PARTNER WHERE TIN=?";

    private PreparedStatement psSave;
    private PreparedStatement psUpdate;
    private PreparedStatement psGet;
    private PreparedStatement psGetByTin;
    private PreparedStatement psGetAll;
    private PreparedStatement psDelete;

    private PartnerDaoImpl() {
    }

    @Override
    public Partner save(Partner partner) throws SQLException {
        psSave = prepareStatement(savePartnerQuery, Statement.RETURN_GENERATED_KEYS);
        //psSave.setLong(1, partner.getTin());
        psSave.setString(1, partner.getName());
        psSave.setString(2, partner.getFullname());
        psSave.setString(3, partner.getTypePartner().toString());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        if (rs.next()) {
            partner.setTin(rs.getLong(1));
        }
        close(rs);
        return partner;
    }

    @Override
    public Partner get(Serializable tin) throws SQLException {
        psGet = prepareStatement(getPartnerQuery);
        psGet.setLong(1, (long)tin);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        if (rs.next()) {
            return populatePartner(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public void update(Partner partner) throws SQLException {
        psUpdate = prepareStatement(updatePartnerQuery);
        psUpdate.setString(1, partner.getName());
        psUpdate.setString(2, partner.getFullname());
        psUpdate.setString(3, partner.getTypePartner().toString());
        psUpdate.setLong(4, partner.getTin());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable tin) throws SQLException {
        psDelete = prepareStatement(deletePartnerQuery);
        psDelete.setLong(1, (long)tin);
        return psDelete.executeUpdate();
    }

    @Override
    public Partner getByTin(long tin) throws SQLException {
        psGetByTin = prepareStatement(getByTinQuery);
        psGetByTin.setLong(1, tin);
        psGetByTin.execute();
        ResultSet rs = psGetByTin.getResultSet();
        Partner partner = new Partner();
        while (rs.next()) {
            partner.setTin(rs.getLong(1));
            partner.setName(rs.getString(2));
            partner.setFullname(rs.getString(3));
            partner.setTypePartner(TypePartner.valueOf(rs.getString(4)));
        }
        close(rs);

        return partner;
    }

    @Override
    public List<Partner> getAll() throws SQLException {
        psGetAll = prepareStatement(getAllPartnerQuery);
        psGetAll.execute();
        ResultSet rs = psGetAll.getResultSet();
        List<Partner> list = new ArrayList<>();
        while (rs.next()) {
            list.add(populatePartner(rs));
        }
        close(rs);
        return list;
    }

    private Partner populatePartner(ResultSet rs) throws SQLException {
        Partner partner = new Partner();
        partner.setTin(rs.getLong(1));
        partner.setName(rs.getString(2));
        partner.setFullname(rs.getString(3));
        partner.setTypePartner(TypePartner.valueOf(rs.getString(4)));
        return partner;
    }

    public static PartnerDao getInstance() {
        PartnerDao partnerDao = INSTANCE;
        if (partnerDao == null) {
            synchronized (PartnerDaoImpl.class) {
                partnerDao = INSTANCE;
                if (partnerDao == null) {
                    INSTANCE = partnerDao = new PartnerDaoImpl();
                }
            }
        }

        return partnerDao;
    }
}
