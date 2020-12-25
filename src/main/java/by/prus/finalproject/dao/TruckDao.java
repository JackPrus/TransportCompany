package by.prus.finalproject.dao;

import by.prus.finalproject.bean.Order;
import by.prus.finalproject.bean.Truck;

public interface TruckDao extends Dao<Truck> {

    Truck readBytruckNo (String truckNo);
    Truck readByOrder (Order order);
    Truck readNotBusy (Boolean isBusy);

}
