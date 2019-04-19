package dao;

import entities.Partner;

import java.sql.SQLException;
import java.util.List;

public interface PartnerDao extends DAO<Partner> {
    Partner getByTin(long tin) throws SQLException;
    List<Partner> getAll() throws SQLException;
}
