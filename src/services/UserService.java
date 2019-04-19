package services;

import entities.User;

public interface UserService {

    User getByLogin(String login);
}
