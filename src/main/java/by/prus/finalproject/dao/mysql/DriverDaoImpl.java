package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.DriverDao;
import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.exception.PersistentException;
import by.prus.finalproject.service.DateParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements requests to database and response from it.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class DriverDaoImpl extends BaseDaoImpl implements DriverDao {

    private static final Logger logger = LogManager.getLogger(DriverDaoImpl.class);

    //ELECT * FROM driver WHERE  isbusy = ?
    private static final String READ_BY_ISBUSY = "SELECT * FROM driver WHERE  isbusy = ?";
    private static final String READ_BY_NAME = "SELECT * FROM driver WHERE  name LIKE ? ORDER BY id";
    private static final String CREATE_DRIVER = "INSERT INTO driver (name, licenseNo, isbusy, medical_aprovement) VALUES (?, ?, ?, ?)";
    private static final String READ_BY_ID = "SELECT * FROM driver WHERE id = (?)";
    private static final String UPDATE_DRIVER = "UPDATE `driver` SET `name` = ?, `licenseNo` = ?, `isbusy` = ?, `medical_aprovement` = ? WHERE `id` = ?";
    private static final String DELETE_DRIVER = "DELETE FROM `driver` WHERE `id` = ?";
    private static final String ORDERS_OF_DRIVER = "SELECT * FROM `driver_has_order` WHERE driver_id = (?)";


    public DriverDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public List<Driver> readBusy(Boolean isbusy) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(READ_BY_ISBUSY);
            statement.setBoolean(1, isbusy);
            resultSet = statement.executeQuery();
            List<Driver> driverList = new ArrayList<>();
            Driver driver = null;
            while (resultSet.next()){
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(resultSet.getInt("id"));
                driverList.add(driver);
            }
            return driverList;

        } catch (SQLException e) {
            logger.error("SQL exception trying to read drivers by name");
            throw new PersistentException(e);
        }finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<Driver> readByName(String searchName) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(READ_BY_NAME);
            statement.setString(1,"%"+searchName+"%");
            resultSet = statement.executeQuery();
            List<Driver>driverList = new ArrayList<>();
            Driver driver = null;
            while (resultSet.next()){
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(resultSet.getInt("id"));
                driverList.add(driver);
            }
            return driverList;
        } catch (SQLException e) {
            logger.error("SQL exception trying to read drivers by name");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Integer create(Driver driver) throws PersistentException {

        DateParser dateParser = new DateParser();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE_DRIVER);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNo());
            statement.setBoolean(3, driver.isBusy());
            statement.setDate(4, new java.sql.Date(driver.getMedicalAprovement().getTime()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `driver`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `driver`");
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public Driver read(Integer identity) throws PersistentException {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_BY_ID);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()){  // получается пустой резалтсет решить проблему!!!
                driver = new Driver(
                        resultSet.getString("name"),
                        resultSet.getString("licenseNo"),
                        resultSet.getBoolean("isbusy"),
                        resultSet.getDate("medical_aprovement")
                );
                driver.setIdentity(identity);
            }

            return driver;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `driver`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException | NullPointerException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }

    }

    @Override
    public void update(Driver driver) throws PersistentException {


        try(PreparedStatement statement = connection.prepareStatement(UPDATE_DRIVER)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNo());
            statement.setBoolean(3, driver.isBusy());
            statement.setDate(4, new java.sql.Date(driver.getMedicalAprovement().getTime()));
            statement.setInt(5, driver.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information {}"+driver);
            throw new PersistentException(e);
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        try(PreparedStatement statement =connection.prepareStatement(DELETE_DRIVER)) {
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'driver' table"+identity);
            throw new PersistentException(e);
        }

    }

    public List<Order> getOrdersForDriver (Driver driver) throws PersistentException {


        try(PreparedStatement statement = connection.prepareStatement(ORDERS_OF_DRIVER)){
            List<Order> orderList = new ArrayList<>();
            statement.setInt(1,driver.getIdentity());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                orderList.add(order);
            }
            return orderList;

        } catch (SQLException e) {
            logger.error("Somethind going wrong tryint to read List of Orders for 'driver' {}"+driver.getIdentity());
            throw new PersistentException(e);
        }
    }

}
