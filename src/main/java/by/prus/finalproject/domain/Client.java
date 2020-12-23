package by.prus.finalproject.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    private int id;
    private String name;
    private String data;
    private ClientType clientType;
    private List<Order> orderList = new ArrayList<>();

    public Client(int id, String name, String data, ClientType clientType) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.clientType = clientType;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public ClientType getClientType() { return clientType; }
    public void setClientType(ClientType clientType) { this.clientType = clientType; }
    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
