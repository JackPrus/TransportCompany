package by.prus.finalproject.bean;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Person {

    private City office;
    private List<Order> orderList = new ArrayList<>();
    private List<Truck> truckList = new ArrayList<>();


    public Manager(String name) {
        super(name);
    }
    public Manager (){}

    public City getOffice() { return office; }
    public void setOffice(City office) { this.office = office; }
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }
    public List<Truck> getTruckList() { return truckList; }
    public void setTruckList(List<Truck> truckList) { this.truckList = truckList; }


}
