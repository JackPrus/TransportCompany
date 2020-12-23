package by.prus.finalproject.domain;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {

    private String passport;
    private List<Order> orderList = new ArrayList<>();
    private List<Truck> truckList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();


    public Manager(int id, String name, String passport) {
        super(name);
        this.passport=passport;
    }

    public String getPassport() { return passport; }
    public void setPassport(String passport) { this.passport = passport; }
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }
    public List<Truck> getTruckList() { return truckList; }
    public void setTruckList(List<Truck> truckList) { this.truckList = truckList; }
    public List<Driver> getDriverList() { return driverList; }
    public void setDriverList(List<Driver> driverList) { this.driverList = driverList; }


}
