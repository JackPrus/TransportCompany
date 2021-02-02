package by.prus.finalproject.dao;

import by.prus.finalproject.bean.User;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;
import java.util.Optional;


public interface UserDao extends Dao<User> {
    User findUserByLoginAndPassword(String login, String password) throws PersistentException;
    List<User> readAllUsers() throws PersistentException;
    void deleteByClientId(Integer identity) throws PersistentException;
}
