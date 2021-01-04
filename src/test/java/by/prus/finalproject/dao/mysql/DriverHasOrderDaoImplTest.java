package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Client;
import by.prus.finalproject.bean.ClientType;
import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.DriverHasOrderDao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class DriverHasOrderDaoImplTest {

    private DriverHasOrderDao dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new DriverHasOrderDaoImpl(connection);
    }

    @AfterClass
    public void afterMethod(){
        wc.closeConnection();
    }


    @Test
    public void testCreate() {

        Order order1 = new Order();
        Order order2 = new Order();
        Driver driver1 = new Driver();
        Driver driver2 = new Driver();
        order1.setIdentity(15);
        driver1.setIdentity(15);
        order2.setIdentity(16);
        driver2.setIdentity(16);

        List<Driver> driverList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        int identy1 = -1;
        int identy2 = -2;
        int identy3 = -3;
        int identy4 = -4;



        try {
            identy1 = dao.create(driver1,order1);
            identy2 = dao.create(driver1,order2);
            identy3 = dao.create(driver2,order1);

            driverList = dao.readDriver(order1);
            orderList = dao.readOrder(driver1);

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(driverList.get(0).getIdentity(),driver1.getIdentity());
        assertEquals(driverList.get(1).getIdentity(), driver2.getIdentity());
        assertEquals(orderList.get(0).getIdentity(),order1.getIdentity());
        assertEquals(orderList.get(1).getIdentity(),order2.getIdentity());

    }

    @Test
    public void testReadDriver() {
    }

    @Test
    public void testReadOrder() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }
}