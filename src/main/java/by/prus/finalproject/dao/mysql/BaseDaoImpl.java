package by.prus.finalproject.dao.mysql;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImpl {

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public void fillOrderListWithID (String sqlForOrders, int identity, List<Order> orderList) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(sqlForOrders)){
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



}
