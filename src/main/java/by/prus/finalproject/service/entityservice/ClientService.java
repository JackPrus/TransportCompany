package by.prus.finalproject.service.entityservice;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public interface ClientService extends Service {
    List<Client> findAll() throws PersistentException;
    List<Client> findByName (String name) throws PersistentException;
    Client findByOrder (Order order) throws PersistentException;

    void save (Client client) throws PersistentException;
    void delete (Integer id) throws PersistentException;

}
