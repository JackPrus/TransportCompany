package by.prus.finalproject.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Driver extends Person {

    private Date medicalAprovement;
    private boolean isBusy;
    private String licenseNo;
    private List<Order> orderList = new ArrayList<>();


    public Driver(){}
    public Driver(String name, String licenseNo, boolean isBusy, Date medicalAprovement) {
        super(name);
        this.licenseNo=licenseNo;
        this.isBusy=isBusy;
        this.medicalAprovement=medicalAprovement;
    }


    public String getLicenseNo() { return licenseNo; }
    public void setLicenseNo(String licenseNo) { this.licenseNo = licenseNo; }
    public Date getMedicalAprovement() { return medicalAprovement; }
    public void setMedicalAprovement(Date medicalAprovement) { this.medicalAprovement = medicalAprovement; }
    public boolean isBusy() { return isBusy; }
    public void setBusy(boolean busy) { isBusy = busy; }

    public List<Order> getOrderList() { return orderList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }
    public void addOrder (Order order){orderList.add(order);}
    public void removeOrder (Order order){orderList.remove(order);}
    public void clearOrderList (){orderList.clear();}




}
