package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.Dao;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.service.DateParser;
import org.testng.annotations.*;

import java.sql.Connection;
import java.util.List;

import static org.testng.Assert.*;

public class DriverDaoImplTest {

    private Dao<Driver> dao;
    private Connection connection;
    private WrapperConnector wc = new WrapperConnector();
    private DateParser dp = new DateParser();

    @BeforeClass
    public void before(){
        connection=wc.getConnection();
        dao = new DriverDaoImpl(connection);
    }


    @Test
    public void testReadNotBusy() {

        List<Driver> trueList = null;
        List<Driver> falseList = null;
        try {
            trueList = ((DriverDaoImpl) dao).readBusy(true);
            falseList = ((DriverDaoImpl) dao).readBusy(false);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        if (trueList.size()>0){
            for (Driver driver : trueList){
                assertEquals(driver.isBusy(), true);
            }
        }

        if (falseList.size()>0){
            for (Driver driver : falseList){
                assertEquals(driver.isBusy(), false);
            }
        }

    }


    @Test
    public void testReadByName() {

        Driver driver1 = new Driver("Тест1", "11111", false, dp.stringToDate("2021-05-25"));
        Driver driver2 = new Driver("Тест2", "22222", true, dp.stringToDate("2021-05-25"));
        Driver driver3 = new Driver("Тест3", "33333", false, dp.stringToDate("2021-05-25"));


        List <Driver> driverList = null;
        int id1 = -1;
        int id2 = -2;
        int id3 = -3;

        try {
            id1 = dao.create(driver1);
            id2 = dao.create(driver2);
            id3 = dao.create(driver3);

            driverList = ((DriverDaoImpl)dao).readByName("Тест");

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(driverList.size(),3);
        assertEquals(driverList.get(0).getName(), driver1.getName() );
        assertEquals(driverList.get(1).getName(), driver2.getName() );
        assertEquals(driverList.get(2).getName(), driver3.getName() );

        try {
            dao.delete(id1);
            dao.delete(id2);
            dao.delete(id3);
        } catch (PersistentException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testCreate() {

        //String name, String licenseNo, boolean isBusy, Date medicalAprovement

        Driver driver1 = new Driver("Василий", "247760", false, dp.stringToDate("2021-05-25"));
        Driver driver2 = null;
        int identy =-1;


        try {
            identy = dao.create(driver1);
            driver1.setIdentity(identy);
            driver2 = dao.read(identy);

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(driver1.getIdentity(),driver2.getIdentity());
        assertEquals(driver1.getName(),driver2.getName());
        assertEquals(driver1.getLicenseNo(),driver2.getLicenseNo());
        assertEquals(driver1.getMedicalAprovement(),driver2.getMedicalAprovement());

    }

    @Test
    public void testRead() {
        Driver driver = null;
        int identy1 = 10;
        try {
            driver = dao.read(identy1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        int identy2 = driver.getIdentity();
        assertEquals(identy1,identy2);

    }

    @Test
    public void testReadUnexisting() {

        int index = 4;
        Driver driver = null;
        try {
            driver = dao.read(index);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(driver, null);

    }


    @Test
    public void testUpdate() {
        Driver driver5 = new Driver();
        driver5.setIdentity(5);
        driver5.setName("Петро");
        driver5.setLicenseNo("5555555");
        driver5.setBusy(true);
        driver5.setMedicalAprovement(dp.stringToDate("2021-05-05"));

        Driver updated = null;

        try {
            dao.update(driver5);
            updated=dao.read(driver5.getIdentity());

        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(updated.getIdentity(), driver5.getIdentity());
        assertEquals(updated.getName(), driver5.getName());
        assertEquals(updated.getLicenseNo(), driver5.getLicenseNo());
        assertEquals(updated.isBusy(), driver5.isBusy());
        assertEquals(updated.getMedicalAprovement(), driver5.getMedicalAprovement());

    }

    @Test
    public void testDelete() {

        Driver driver = null;

        try {
            dao.delete(4);
            driver=dao.read(4);
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        assertEquals(driver, null);

    }

//    @AfterMethod
//    public void after (){}{
//        wc.closeConnection();
//    }

}