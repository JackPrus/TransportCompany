package by.prus.finalproject.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Truck extends Entity {

    private String truckNo; //1
    private int lengthCapacity;  //2cm
    private int widthCapacity; //3cm
    private int heightCapacity; //4cm
    private int weighCapacity; //5kg
    private boolean isBusy; //6
    private Manager manager; //7
    private List<Order>orderList = new ArrayList<>();

    public Truck(String truckNo, int lengthCapacity, int widthCapacity, int heightCapacity, int weighCapacity, boolean isBusy) {
        this.truckNo = truckNo;
        this.lengthCapacity = lengthCapacity;
        this.widthCapacity = widthCapacity;
        this.heightCapacity = heightCapacity;
        this.weighCapacity = weighCapacity;
        this.isBusy=isBusy;
    }

    public String getTruckNo() { return truckNo; }
    public void setTruckNo(String truckNo) { this.truckNo = truckNo; }
    public int getLengthCapacity() { return lengthCapacity; }
    public void setLengthCapacity(int lengthCapacity) { this.lengthCapacity = lengthCapacity; }
    public int getWidthCapacity() { return widthCapacity; }
    public void setWidthCapacity(int widthCapacity) { this.widthCapacity = widthCapacity; }
    public int getHeightCapacity() { return heightCapacity; }
    public void setHeightCapacity(int heightCapacity) { this.heightCapacity = heightCapacity; }
    public int getWeighCapacity() { return weighCapacity; }
    public void setWeighCapacity(int weighCapacity) { this.weighCapacity = weighCapacity; }
    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }

    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }


}
