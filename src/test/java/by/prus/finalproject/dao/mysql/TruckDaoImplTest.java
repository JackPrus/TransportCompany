package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.PersistentException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class TruckDaoImplTest {

    private Dao<Truck> dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new TruckDaoImpl(connection);
    }

    @AfterClass
    public void afterMethod(){
        wc.closeConnection();
    }


    @Test
    public void testReadBytruckNo() {
    }

    @Test
    public void testReadByOrder() {
    }

    @Test
    public void testReadNotBusy() {
    }

//truck_no, `length_capasity(cm)`, `width_capasity(cm)`, `height_capasity(cm)`, isbusy, manager_id) VALUES (?,?,?,?,?,?,?)";

    @Test
    public void testCreate() {

        Manager manager1 = new Manager();
        manager1.setIdentity(1);

        Truck truck1 = new Truck();
        truck1.setTruckNo("254-589");
        truck1.setLengthCapacity(1300);
        truck1.setWidthCapacity(245);
        truck1.setHeightCapacity(275);
        truck1.setWeighCapacity(22000);
        truck1.setBusy(false);
        truck1.setManager(manager1);

        Truck truck2 = null;
        int identy = -1;

        try {
            identy = dao.create(truck1);
            truck1.setIdentity(identy);
            truck2 = dao.read(identy);

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(truck1.getIdentity(),truck2.getIdentity());
        assertEquals(truck1.getTruckNo(),truck2.getTruckNo());
        assertEquals(truck1.getLengthCapacity(), truck2.getLengthCapacity());
        assertEquals(truck1.getWidthCapacity(), truck2.getWidthCapacity());
        assertEquals(truck1.getHeightCapacity(), truck2.getHeightCapacity());
        assertEquals(truck1.getWeighCapacity(), truck2.getWeighCapacity());
        assertEquals(truck1.isBusy(), truck2.isBusy());
        assertEquals(truck1.getManager().getIdentity(), truck2.getManager().getIdentity());

    }

    @Test
    public void testRead() {

        Truck truck = null;
        int identy1 =1;
        try{
            truck = dao.read(identy1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        int identy2 = truck.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testUpdate() {

        Manager manager4 = new Manager();
        manager4.setName("Инокентий");
        manager4.setOffice(City.MINSK);
        manager4.setIdentity(1);

        Truck truck3 = new Truck();
        truck3.setIdentity(3);

        truck3.setTruckNo("333-333");
        truck3.setLengthCapacity(550);
        truck3.setWidthCapacity(220);
        truck3.setHeightCapacity(260);
        truck3.setWeighCapacity(3500);
        truck3.setBusy(false);
        truck3.setManager(manager4);

        Truck updated = null;

        try{
            dao.update(truck3);
            updated = dao.read(3);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(updated.getIdentity(),truck3.getIdentity());
        assertEquals(updated.getTruckNo(),truck3.getTruckNo());
        assertEquals(updated.getLengthCapacity(), truck3.getLengthCapacity());
        assertEquals(updated.getWidthCapacity(), truck3.getWidthCapacity());
        assertEquals(updated.getHeightCapacity(), truck3.getHeightCapacity());
        assertEquals(updated.getWeighCapacity(), truck3.getWeighCapacity());
        assertEquals(updated.isBusy(), truck3.isBusy());
        assertEquals(updated.getManager().getIdentity(), truck3.getManager().getIdentity());

    }

    @Test
    public void testDelete() {
    }
}