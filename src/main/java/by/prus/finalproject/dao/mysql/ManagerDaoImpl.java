package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.dao.ManagerDao;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl extends BaseDaoImpl implements ManagerDao {

    private static final Logger logger = LogManager.getLogger(ManagerDaoImpl.class);

    @Override
    public List<Manager> findByName(String name) {
        return null;
    }

    @Override
    public Integer create(Manager manager) throws PersistentException {
        String sql = "INSERT INTO manager (name,office) VALUE (?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, manager.getName());
            statement.setString(2,manager.getOffice().getCityName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `manager`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `manager`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    @Override
    public Manager read(Integer identity) throws PersistentException {
        String sql = "SELECT * FROM manager WHERE id = (?)";
        String sqlForOrders = "SELECT id FROM order WHERE manager_id = (?)";
        String sqlForTrucks = "SELECT id FROM truck WHERE manager_id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Manager manager = null;
            //truck_no, `length_capasity(cm)`, `width_capasity(cm)`, `height_capasity(cm)`, isbusy, manager_id
            if (resultSet.next()){
                manager = new Manager();
                List<Order> orderList = new ArrayList<>();
                List<Truck> truckList = new ArrayList<>();
                fillOrderListWithID(sqlForOrders,identity, orderList);
                fillTruckListWithID(sqlForTrucks,identity,truckList);

                manager.setName(resultSet.getString("name"));
                manager.setOffice(City.getCity(resultSet.getString("office")));
                manager.setIdentity(resultSet.getInt("id"));
                manager.setOrderList(orderList);
                manager.setTruckList(truckList);
            }
            return manager;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `manager`");
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
            } catch(SQLException e) {}
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    @Override
    public void update(Manager manager) throws PersistentException {

        String sql = "UPDATE `manager` SET `name` = ?, `office` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, manager.getName());
            statement.setString(2, manager.getOffice().getCityName());
            statement.setInt(3,manager.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information of in manager table ");
            throw new PersistentException(e);
        } finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM `manager` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'order' table"+identity);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }

    }
}
