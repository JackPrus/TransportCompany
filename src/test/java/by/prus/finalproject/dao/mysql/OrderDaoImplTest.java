package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;

import java.math.BigDecimal;
import java.util.Date;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class OrderDaoImplTest {

    private Dao<Order> dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new OrderDaoImpl(connection);
    }

    @AfterClass
    public void afterMethod(){
        wc.closeConnection();
    }
//  `pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm,
//  weight_kg, date, isactive, price, truck_id, manager_id, client_id

    @Test
    public void testCreate() {

        Truck truck = new Truck();
        Manager manager = new Manager();
        Client client = new Client();

        truck.setIdentity(1);
        manager.setIdentity(1);
        client.setIdentity(1);

        Order order1 = new Order();
        order1.setPickupAdress("Катунина 3");
        order1.setCityPickUp(City.HOMEL);
        order1.setCityDelivery(City.MAZYR);
        order1.setUnloadingAdress("Бульвар Дружбы 5");
        order1.setLength(25);
        order1.setWidth(20);
        order1.setHeight(15);
        order1.setWeight(2.15);
        order1.setOrderDate(new Date());
        order1.setActive(true);
        order1.setPrice(new BigDecimal("15.50"));
        order1.setTruck(truck);
        order1.setManager(manager);
        order1.setClient(client);

        Order order2 = null;
        int identy = -1;

        try {
            identy = dao.create(order1);
            order1.setIdentity(identy);
            order2 = dao.read(identy);

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(order1.getIdentity(), order2.getIdentity());
        assertEquals(order1.getPickupAdress(), order2.getPickupAdress());
        assertEquals(order1.getCityPickUp(), order2.getCityPickUp());
        assertEquals(order1.getCityDelivery(), order2.getCityDelivery());
        assertEquals(order1.getLength(), order2.getLength());
        assertEquals(order1.getWidth(), order2.getWidth());
        assertEquals(order1.getHeight(), order2.getHeight());
        assertEquals(order1.getWeight(),order2.getWeight());
        assertEquals(order1.isActive(),order2.isActive());
        assertEquals(order1.getPrice(), order2.getPrice());
        assertEquals(order1.getManager().getIdentity(), order2.getManager().getIdentity());
        assertEquals(order1.getTruck().getIdentity(), order2.getTruck().getIdentity());
        assertEquals(order1.getClient().getIdentity(),order2.getClient().getIdentity());


    }

    @Test
    public void testRead() {

        Order order = new Order();
        int identy1 = 1;

        try{
            order = dao.read(identy1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        int identy2 = order.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testUpdate() {

        Truck truck = new Truck();
        Manager manager = new Manager();
        Client client = new Client();

        truck.setIdentity(1);
        manager.setIdentity(1);
        client.setIdentity(1);

        Order order1 = new Order();
        order1.setPickupAdress("Страконицкий 11");
        order1.setCityPickUp(City.MAZYR);
        order1.setCityDelivery(City.HOMEL);
        order1.setUnloadingAdress("Красноармейская 4а");
        order1.setLength(11);
        order1.setWidth(21);
        order1.setHeight(11);
        order1.setWeight(1.15);
        order1.setOrderDate(new Date());
        order1.setActive(true);
        order1.setPrice(new BigDecimal("15.50"));
        order1.setTruck(truck);
        order1.setManager(manager);
        order1.setClient(client);
        order1.setIdentity(1);

        Order updated = null;

        try{
            dao.update(order1);
            updated = dao.read(1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(order1.getIdentity(), updated.getIdentity());
        assertEquals(order1.getPickupAdress(), updated.getPickupAdress());
        assertEquals(order1.getCityPickUp(), updated.getCityPickUp());
        assertEquals(order1.getCityDelivery(), updated.getCityDelivery());
        assertEquals(order1.getLength(), updated.getLength());
        assertEquals(order1.getWidth(), updated.getWidth());
        assertEquals(order1.getHeight(), updated.getHeight());
        assertEquals(order1.getWeight(),updated.getWeight());
        assertEquals(order1.isActive(),updated.isActive());
        assertEquals(order1.getPrice(), updated.getPrice());
        assertEquals(order1.getManager().getIdentity(), updated.getManager().getIdentity());
        assertEquals(order1.getTruck().getIdentity(), updated.getTruck().getIdentity());
        assertEquals(order1.getClient().getIdentity(),updated.getClient().getIdentity());

    }

    @Test
    public void testDelete() {

        Order order = null;

        try {
            dao.delete(5);
            order=dao.read(5);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(order, null);

    }

    @Test
    public void testGetDriversForOrder() {
    }
}