package by.prus.finalproject.bean;

import javax.servlet.RequestDispatcher;
import java.math.BigDecimal;
import java.util.Date;

public class RequestForQuotation {

    //нет веса нет адресов , нет исактив, нет цены, нет трака, нет менеджера

    private City cityPickUp;
    private City cityDelivery;
    private int length; //cm
    private int width; //cm
    private int height; //cm
    private double weight; // kg
    private Client client;

    public RequestForQuotation (){}

    public RequestForQuotation(City cityPickUp, City cityDelivery, int length, int width, int height, double weight, Client client) {
        this.cityPickUp = cityPickUp;
        this.cityDelivery = cityDelivery;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.client = client;
    }

    public City getCityPickUp() { return cityPickUp; }
    public void setCityPickUp(City cityPickUp) { this.cityPickUp = cityPickUp; }
    public City getCityDelivery() { return cityDelivery; }
    public void setCityDelivery(City cityDelivery) { this.cityDelivery = cityDelivery; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
