package by.prus.finalproject.dao;

import by.prus.finalproject.domain.Order;
import by.prus.finalproject.domain.Truck;

public interface TruckDao extends Dao<Truck> {

    Truck readBytruckNo (String truckNo);
    Truck readByOrder (Order order);
    Truck readByLength (int length);
    Truck readByHeight (int height);
    Truck readByWeight (int height);
    Truck readNotBusy (Boolean isBusy);

}
