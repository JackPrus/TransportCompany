package by.prus.finalproject.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client extends Entity {


    private String name;
    private String data;
    private ClientType clientType;
    private List<Order> orderList = new ArrayList<>();

    public Client(){}
    public Client( String name, String data, ClientType clientType) {
        this.name = name;
        this.data = data;
        this.clientType = clientType;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public ClientType getClientType() { return clientType; }
    public void setClientType(ClientType clientType) { this.clientType = clientType; }
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

}
