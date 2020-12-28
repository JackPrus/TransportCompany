package by.prus.finalproject.controller;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.dao.pool.WrapperConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        Date date = new Date();
        System.out.println(date.getTime());


    }

    public static void printOrder (List<Order> orders){
        for (Order o : orders){
            System.out.println(o.getIdentity());
        }
    }

}
