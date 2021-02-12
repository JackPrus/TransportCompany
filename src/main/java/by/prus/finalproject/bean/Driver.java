package by.prus.finalproject.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bean clas means Driver of company
 * @autor Dzmitry Prus
 * @version 1.0
 */
public class Driver extends Person {

    private Date medicalAprovement;
    private boolean isBusy;
    private String licenseNo;


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





}
