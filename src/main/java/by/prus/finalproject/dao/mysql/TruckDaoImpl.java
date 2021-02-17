package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.dao.TruckDao;
import by.prus.finalproject.bean.Manager;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements requests to database and response from it.
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class TruckDaoImpl extends BaseDaoImpl implements TruckDao {

    private static final Logger logger = LogManager.getLogger(TruckDaoImpl.class);

    private static final String CREATE = "INSERT INTO truck (truck_no, `length_capasity(cm)`, `width_capasity(cm)`, `height_capasity(cm)`, `weight_capasity(kg)`, isbusy, manager_id) VALUES (?,?,?,?,?,?,?)";
    private static final String READ = "SELECT * FROM truck WHERE id = (?)";
    private static final String READ_ORDERS_OF_TRUCK = "SELECT * FROM sdek.order WHERE truck_id = (?)";
    private static final String UPDATE = "UPDATE `truck` SET `truck_no` = ?, `length_capasity(cm)` = ?, `width_capasity(cm)` = ?, `height_capasity(cm)` = ?, `weight_capasity(kg)`=?, isbusy = ?, manager_id =? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `truck` WHERE `id` = ?";
    private static final String READ_ALL_TRUCKS = "SELECT * FROM truck";
    private static final String TRUCKS_OF_MANAGER = "SELECT * FROM truck where manager_id = ?";

    public TruckDaoImpl (Connection connection){
        super(connection);
    }

    @Override
    public Integer create(Truck truck) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE);
            statement.setString(1, truck.getTruckNo());
            statement.setInt(2, truck.getLengthCapacity());
            statement.setInt(3,truck.getWidthCapacity());
            statement.setInt(4,truck.getHeightCapacity());
            statement.setInt(5,truck.getWeightCapacity());
            statement.setBoolean(6, truck.isBusy());
            statement.setInt(7,truck.getManager().getIdentity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `truck`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `truck`");
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
    public Truck read(Integer identity) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Truck truck = null;
            if (resultSet.next()){
                truck = new Truck();

                Manager manager = new Manager();
                manager.setIdentity(resultSet.getInt("manager_id"));
                List<Order> orderList = new ArrayList<>();
                fillOrderListWithID(READ_ORDERS_OF_TRUCK, identity, orderList);

                truck.setTruckNo(resultSet.getString("truck_no"));
                truck.setLengthCapacity(resultSet.getInt("length_capasity(cm)"));
                truck.setWidthCapacity(resultSet.getInt("width_capasity(cm)"));
                truck.setHeightCapacity(resultSet.getInt("height_capasity(cm)"));
                truck.setWeightCapacity(resultSet.getInt("weight_capasity(kg)"));
                truck.setBusy(resultSet.getBoolean("isbusy"));
                truck.setManager(manager);
                truck.setIdentity(identity);
                truck.setOrderList(orderList);

            }
            return truck;
        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `truck`");
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
    public void update(Truck truck) throws PersistentException {

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, truck.getTruckNo());
            statement.setInt(2, truck.getLengthCapacity());
            statement.setInt(3, truck.getWidthCapacity());
            statement.setInt(4,truck.getHeightCapacity());
            statement.setInt(5,truck.getWeightCapacity());
            statement.setBoolean(6,truck.isBusy());
            statement.setInt(7,truck.getManager().getIdentity());
            statement.setInt(8,truck.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information of in truck table ");
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
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'truck' table"+identity);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            } catch(SQLException e) {}
        }
    }

    public List<Truck> readAllTrucks() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Truck> truckList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(READ_ALL_TRUCKS);
            resultSet = statement.executeQuery();
            Truck truck = null;
            while (resultSet.next()){
                truck = new Truck();

                Manager manager = new Manager();
                manager.setIdentity(resultSet.getInt("manager_id"));

                truck.setIdentity(resultSet.getInt("id"));
                truck.setTruckNo(resultSet.getString("truck_no"));
                truck.setLengthCapacity(resultSet.getInt("length_capasity(cm)"));
                truck.setWidthCapacity(resultSet.getInt("width_capasity(cm)"));
                truck.setHeightCapacity(resultSet.getInt("height_capasity(cm)"));
                truck.setWeightCapacity(resultSet.getInt("weight_capasity(kg)"));
                truck.setBusy(resultSet.getBoolean("isbusy"));
                truck.setManager(manager);

                truckList.add(truck);

            }
            return truckList;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `truck`");
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

    public List<Truck> readByManagerId(int managerId) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Truck> truckList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(TRUCKS_OF_MANAGER);
            statement.setInt(1,managerId);
            resultSet = statement.executeQuery();
            Truck truck = null;
            while (resultSet.next()){
                truck = new Truck();

                Manager manager = new Manager();
                manager.setIdentity(managerId);

                truck.setIdentity(resultSet.getInt("id"));
                truck.setTruckNo(resultSet.getString("truck_no"));
                truck.setLengthCapacity(resultSet.getInt("length_capasity(cm)"));
                truck.setWidthCapacity(resultSet.getInt("width_capasity(cm)"));
                truck.setHeightCapacity(resultSet.getInt("height_capasity(cm)"));
                truck.setWeightCapacity(resultSet.getInt("weight_capasity(kg)"));
                truck.setBusy(resultSet.getBoolean("isbusy"));
                truck.setManager(manager);

                truckList.add(truck);

            }
            return truckList;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `truck`");
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

}
