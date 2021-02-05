package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public interface TruckDao extends Dao<Truck> {

    Truck read(Integer identity) throws PersistentException;
    List<Truck> readByManagerId(int managerId) throws PersistentException;

}
