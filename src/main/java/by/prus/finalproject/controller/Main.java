package by.prus.finalproject.controller;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.RequestForQuotation;
import by.prus.finalproject.bean.Truck;
import by.prus.finalproject.dao.mysql.DaoHelperFactory;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.entityservice.OrderService;
import by.prus.finalproject.service.entityservice.TruckService;
import by.prus.finalproject.service.quotation.RfqService;

import javax.xml.rpc.holders.BigDecimalHolder;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ServiceException {
        Truck truck = new Truck();
        truck.setIdentity(6);

        TruckService truckService = new TruckService(new DaoHelperFactory());
        truck = truckService.read(truck.getIdentity());

        System.out.println(truck.isBusy());

        OrderService orderService = new OrderService(new DaoHelperFactory());

        List <Order> ordrersOfTruck = orderService.getOrdersOfTruck(truck);
        System.out.println(ordrersOfTruck.size());

    }
}

