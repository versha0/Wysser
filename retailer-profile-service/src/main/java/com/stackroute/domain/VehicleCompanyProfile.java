package com.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehicleCompanyProfile {

    @Id
    private int id;
    private String companyName;
    private  String contact;
    private String address;
    private String gst;
    private String email;

    public VehicleCompanyProfile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    @Override
    public String toString() {
        return "VehicleCompanyProfile{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", gst='" + gst + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
