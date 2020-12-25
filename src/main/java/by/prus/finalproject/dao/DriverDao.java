package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.exception.PersistentException;

import java.util.List;

public interface DriverDao extends Dao<Driver> {

    List<Driver> readBusy(Boolean isbusy) throws PersistentException;
    List<Driver> readByName(String name) throws PersistentException;

}
