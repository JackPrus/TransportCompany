package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.ConnectionPool;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class ManagerDaoImplTest {

    Dao dao;
    ConnectionPool cpool;
    DaoHelper daoHelper;

    @BeforeClass
    public void before(){
        try {
            cpool = ConnectionPool.getInstance();
            daoHelper = new DaoHelper(cpool);
            dao = daoHelper.createManagerDao();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterMethod(){
        daoHelper.close();
    }



    @Test
    public void testFindByName() {
    }

    @Test
    public void testCreate() {

        Manager manager1 = new Manager();
        manager1.setName("Иван");
        manager1.setOffice(City.BOBRUISK);

        Manager manager2 = null;
        int identy = 5;

        try {
            identy = dao.create(manager1);
            manager1.setIdentity(identy);
            manager2 = (Manager) dao.read(identy);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(manager1.getIdentity(),manager2.getIdentity());
        assertEquals(manager1.getName(),manager2.getName());
        assertEquals(manager1.getOffice(),manager2.getOffice());

    }

    @Test
    public void testRead() {

        Manager manager = null;
        int identy1 = 1;

        try{
            manager = (Manager) dao.read(identy1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        int identy2 = manager.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testUpdate() {

        Manager manager4 = new Manager();
        manager4.setName("Инокентий");
        manager4.setOffice(City.MINSK);
        manager4.setIdentity(1);

        Manager updated = null;

        try{
            dao.update(manager4);
            updated = (Manager) dao.read(1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(updated.getIdentity(), manager4.getIdentity());
        assertEquals(updated.getName(), manager4.getName());
        assertEquals(updated.getOffice(), manager4.getOffice());

    }

    @Test
    public void testDelete() {

        Manager manager = null;

        try {
            dao.delete(4);
            manager= (Manager) dao.read(4);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(manager, null);

    }
}