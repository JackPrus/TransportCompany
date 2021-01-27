package by.prus.finalproject.controller;

import by.prus.finalproject.bean.City;
import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.RequestForQuotation;
import by.prus.finalproject.dao.pool.WrapperConnector;
import by.prus.finalproject.exception.ServiceException;
import by.prus.finalproject.service.quotation.RfqService;

import javax.xml.rpc.holders.BigDecimalHolder;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BigDecimal price = new BigDecimal("0.01");
        System.out.println(price);
    }
}

