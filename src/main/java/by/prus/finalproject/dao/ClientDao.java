package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public interface ClientDao extends Dao<Client> {

    List<Client> readAll() throws PersistentException;

}
