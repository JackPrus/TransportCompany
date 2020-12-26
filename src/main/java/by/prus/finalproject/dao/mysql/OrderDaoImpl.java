package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.*;
import by.prus.finalproject.dao.OrderDao;
import by.prus.finalproject.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

    private static final Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    @Override
    public Integer create(Order order) throws PersistentException {
        String sql = "INSERT INTO `order` (`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm, weight_kg, date, isactive, price, truck_id, manager_id, client_id) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            if (order.getPickupAdress()!=null){
                statement.setString(1,order.getPickupAdress());
            }
            statement.setString(2, order.getCityPickUp().getCityName());
            statement.setString(3,order.getCityDelivery().getCityName());
            if (order.getUnloadingAdress()!=null){
                statement.setString(4,order.getUnloadingAdress());
            }
            statement.setInt(5, order.getLength());
            statement.setInt(6,order.getWidth());
            statement.setInt(7, order.getHeight());
            statement.setDouble(8,order.getWeight());
            statement.setDate(9,new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBoolean(10, order.isActive());
            statement.setBigDecimal(11, order.getPrice());
            statement.setInt(12, order.getTruck().getIdentity());
            statement.setInt(13, order.getManager().getIdentity());
            statement.setInt(14,order.getClient().getIdentity());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `order`");
                throw new PersistentException();
            }
        } catch(SQLException e) {
            logger.error("SQL exception trying to create field in table `order`");
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

    //`pickup adress`,city_pickup,city_delivery, `unloading adress`, length_cm, width_cm, height_cm,
    // weight_kg, date, isactive, price, truck_id, manager_id, client_id

    @Override
    public Order read(Integer identity) throws PersistentException {
        String sql = "SELECT * FROM `order` WHERE id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, identity);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()){

                order = new Order();
                Truck truck = new Truck();
                Manager manager = new Manager();
                Client client = new Client();

                truck.setIdentity(resultSet.getInt("truck_id"));
                manager.setIdentity(resultSet.getInt("manager_id"));
                client.setIdentity(resultSet.getInt("client_id"));

                order.setIdentity(identity);
                order.setPickupAdress(resultSet.getString("pickup adress"));
                order.setCityPickUp(City.getCity(resultSet.getString("city_pickup")));
                order.setCityDelivery(City.getCity(resultSet.getString("city_delivery")));
                order.setUnloadingAdress(resultSet.getString("unloading adress"));
                order.setLength(resultSet.getInt("length_cm"));
                order.setWidth(resultSet.getInt("width_cm"));
                order.setHeight(resultSet.getInt("height_cm"));
                order.setWeight(resultSet.getDouble("weight_kg"));
                order.setOrderDate(resultSet.getDate("date"));
                order.setActive(resultSet.getBoolean("isactive"));
                order.setPrice(resultSet.getBigDecimal("price"));
                order.setTruck(truck);
                order.setManager(manager);
                order.setClient(client);

            }
            return order;

        } catch(SQLException e) {
            logger.error("SQL exception trying to read from `order`");
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
    public void update(Order order) throws PersistentException {

        String sql = "UPDATE `order` SET `pickup adress`=?,city_pickup=?,city_delivery=?, `unloading adress`=?, length_cm=?, width_cm=?, height_cm=?, weight_kg=?, date=?, isactive=?, price=?, truck_id=?, manager_id=?, client_id=? WHERE `id` = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            if (order.getPickupAdress()!=null){
                statement.setString(1,order.getPickupAdress());
            }
            statement.setString(2, order.getCityPickUp().getCityName());
            statement.setString(3,order.getCityDelivery().getCityName());
            if (order.getUnloadingAdress()!=null){
                statement.setString(4,order.getUnloadingAdress());
            }
            statement.setInt(5, order.getLength());
            statement.setInt(6,order.getWidth());
            statement.setInt(7, order.getHeight());
            statement.setDouble(8,order.getWeight());
            statement.setDate(9,new java.sql.Date(order.getOrderDate().getTime()));
            statement.setBoolean(10, order.isActive());
            statement.setBigDecimal(11, order.getPrice());
            statement.setInt(12, order.getTruck().getIdentity());
            statement.setInt(13, order.getManager().getIdentity());
            statement.setInt(14,order.getClient().getIdentity());
            statement.setInt(15, order.getIdentity());
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Error trying to update information of in order table ");
            throw new PersistentException(e);
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM `order` WHERE `id` = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch(SQLException e) {
            logger.error("Somethind going wrong doing delete position {} in 'order' table"+identity);
            throw new PersistentException(e);
        }

    }
}
