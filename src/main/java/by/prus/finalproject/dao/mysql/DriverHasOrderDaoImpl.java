package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Driver;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.DriverHasOrderDao;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverHasOrderDaoImpl extends BaseDaoImpl implements DriverHasOrderDao {

    private static final Logger logger = LogManager.getLogger(DriverHasOrderDaoImpl.class);

    private static final String CREATE = "INSERT INTO sdek.driver_has_order (driver_id, order_id) VALUES (?, ?)";
    private static final String READ_DRIVER = "SELECT * FROM sdek.driver_has_order WHERE  order_id = (?) ORDER BY driver_id";
    private static final String READ_ORDER = "SELECT * FROM sdek.driver_has_order WHERE  driver_id = (?) ORDER BY driver_id";
    private static final String UPDATE = "UPDATE `sdek`.driver_has_order` SET driver_id = (?), order_id = (?) WHERE id_truck_driver = (?)";
    private static final String DELETE = "DELETE FROM sdek.`driver_has_order` WHERE `id_truck_driver` = ?";

    public DriverHasOrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Integer create(Driver driver, Order order) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setInt(1, driver.getIdentity());
            statement.setInt(2,order.getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `driver_has_order`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `driver_has_order`");
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
    public List<Driver> readDriver(Order order) throws PersistentException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_DRIVER)){
            statement.setInt(1, order.getIdentity());
            resultSet = statement.executeQuery();
            List<Driver>driverList = new ArrayList<>();
            Driver driver = null;
            while (resultSet.next()){
                driver = new Driver();
                driver.setIdentity(resultSet.getInt("driver_id"));
                driverList.add(driver);
            }
            return driverList;
        } catch (SQLException e) {
            logger.error("SQL exception trying to read list of Drivers by order");
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Order> readOrder(Driver driver) throws PersistentException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ORDER)){
            statement.setInt(1, driver.getIdentity());
            resultSet = statement.executeQuery();
            List<Order>orderList = new ArrayList<>();
            Order order = null;
            while (resultSet.next()){
                order = new Order();
                order.setIdentity(resultSet.getInt("order_id"));
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            logger.error("SQL exception trying to read List of orders with driver");
            throw new PersistentException(e);
        }
    }


    //TODO !!! swap id_trudck_driver to just 'id'

    @Override
    public void update(Integer id, Driver driver, Order order) throws PersistentException {

        try(PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, driver.getIdentity());
            statement.setInt(2, order.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information in 'driver_has_order'");
            throw new PersistentException(e);
        }

    }

    @Override
    public void delete(Integer id) throws PersistentException {

        try(PreparedStatement statement =connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'driver_has_order' table"+id);
            throw new PersistentException(e);
        }

    }

}
