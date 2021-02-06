package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.exception.ServiceException;

import java.util.List;

public interface OrderDao extends Dao<Order> {

    public List<Order> getOrdersByClient(Client client) throws PersistentException;
    public List<Order> getOrdersOfManager(Manager manager) throws PersistentException;
    public List<Order> getOrdersWithoutManager () throws PersistentException;
    public List<Order> getOrdersOfTruck(Truck truck) throws PersistentException;
    public List<Order> getCurrentOrdersOfTruck(Truck truck) throws ServiceException, PersistentException;

}
