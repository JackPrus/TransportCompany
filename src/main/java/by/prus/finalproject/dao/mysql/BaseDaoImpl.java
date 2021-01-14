package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.dao.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImpl {

    protected Connection connection;
    private static final Logger logger = LogManager.getLogger(BaseDaoImpl.class);

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public BaseDaoImpl (Connection connection){
        this.connection=connection;
    }

    public void fillOrderListWithID (String sql, int identity, List<Order> orderList) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,identity);
            ResultSet orderResultSet = ps.executeQuery();
            while (orderResultSet.next()){
                Order order = new Order();
                order.setIdentity(orderResultSet.getInt("id"));
                orderList.add(order);
            }
        }
    }

    public void fillTruckListWithID (String sqlForTrucks, int identity, List<Truck> truckList) throws SQLException {

        try(PreparedStatement ps = connection.prepareStatement(sqlForTrucks)){
            ps.setInt(1,identity);
            ResultSet truckResultSet = ps.executeQuery();
            while (truckResultSet.next()){
                Truck truck = new Truck();
                truck.setIdentity(truckResultSet.getInt("id"));
                truckList.add(truck);
            }
        }

    }

    public void createDriverOrder (int driverId, int orderId){
        String sql = "INSERT INTO driver_has_order (`driver_id`, `order_id`) VALUES (?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,driverId);
            statement.setInt(2,orderId);
            statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQL exception trying to create `driver_has_order`");
        }
    }



}
