package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    public List<Order> getOrdersByClient(Client client) throws PersistentException;
    public List<Order> getOrdersOfManager(Manager manager) throws PersistentException;
    public List<Order> getOrdersWithoutManager () throws PersistentException;

}
