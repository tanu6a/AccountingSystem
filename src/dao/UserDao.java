package dao;

import java.sql.SQLException;

import entities.User;

public interface UserDao extends DAO<User> {
    User getByLogin(String login) throws SQLException;
}
