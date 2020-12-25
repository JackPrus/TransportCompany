package by.prus.finalproject.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order extends Entity {

    private String pickupAdress;
    private String cityPickUp;
    private String cityDelivery;
    private String unloadingAdress;
    private int length; //cm
    private int width; //cm
    private int height; //cm
    private double weight; // kg
    private Date orderDate;
    private boolean isActive;
    private BigDecimal price;
    private Truck truck;
    private Manager manager;
    private Client client;

    public Order (){}

    public String getPickupAdress() { return pickupAdress; }
    public void setPickupAdress(String pickupAdress) { this.pickupAdress = pickupAdress; }
    public String getCityPickUp() { return cityPickUp; }
    public void setCityPickUp(String cityPickUp) { this.cityPickUp = cityPickUp; }
    public String getCityDelivery() { return cityDelivery; }
    public void setCityDelivery(String cityDelivery) { this.cityDelivery = cityDelivery; }
    public String getUnloadingAdress() { return unloadingAdress; }
    public void setUnloadingAdress(String unloadingAdress) { this.unloadingAdress = unloadingAdress; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Truck getTruck() { return truck; }
    public void setTruck(Truck truck) { this.truck = truck; }
    public Manager getManager() { return manager; }
    public void setManager(Manager manager) { this.manager = manager; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
