package dao.impl;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDao;
import entities.User;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static volatile UserDao INSTANCE = null;

    private static final String getUser = "SELECT * FROM USER WHERE LOGIN=?";
    private PreparedStatement psGetByLogin;

    @Override
    public User getByLogin(String login) throws SQLException {
        psGetByLogin = prepareStatement(getUser);
        psGetByLogin.setString(1, login);
        ResultSet rs = psGetByLogin.executeQuery();
        if (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);

        return null;
    }

    @Override
    public User save(User user) throws SQLException {
        return null;
    }

    @Override
    public User get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(User user) throws SQLException {

    }

    @Override
    public int delete(Serializable id) throws SQLException {
        return 0;
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setName(rs.getString(2));
        entity.setSurname(rs.getString(3));
        entity.setLogin(rs.getString(4));
        entity.setPassword(rs.getString(5));
        entity.setStatus(rs.getString(6));
        entity.setRole(rs.getString(7));
        return entity;
    }

    public static UserDao getInstance() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }

        return userDao;
    }
}