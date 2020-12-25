package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Manager;

import java.util.List;

public interface ManagerDao extends Dao<Manager> {

    List<Manager> findByName (String name);

}
