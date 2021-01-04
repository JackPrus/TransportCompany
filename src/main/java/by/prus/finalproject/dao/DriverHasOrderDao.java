package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;


public interface DriverHasOrderDao {

    Integer create(Driver driver, Order order) throws PersistentException;
    List<Driver> readDriver(Order order) throws PersistentException;
    List<Order> readOrder (Driver driver) throws PersistentException;

    void update (Integer id, Driver driver, Order order) throws PersistentException;
    void delete (Integer id) throws PersistentException;


}
