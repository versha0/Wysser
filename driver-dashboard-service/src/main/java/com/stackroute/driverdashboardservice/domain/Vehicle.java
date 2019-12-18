package com.stackroute.driverdashboardservice.domain;

//@Entity
public class Vehicle {
    int id;
    String vehicleNumber;
    String driverName;
    String vehicleType;
    int capacity;
    int costPerSlot;
    String vehicleStatus;

    String date;
    String slot1Status;
    String slot2Status;
    String slot3Status;

    public Vehicle() { }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlot1Status() {
        return slot1Status;
    }

    public void setSlot1Status(String slot1Status) {
        this.slot1Status = slot1Status;
    }

    public String getSlot2Status() {
        return slot2Status;
    }

    public void setSlot2Status(String slot2Status) {
        this.slot2Status = slot2Status;
    }

    public String getSlot3Status() {
        return slot3Status;
    }

    public void setSlot3Status(String slot3Status) {
        this.slot3Status = slot3Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCostPerSlot() {
        return costPerSlot;
    }

    public void setCostPerSlot(int costPerSlot) {
        this.costPerSlot = costPerSlot;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}


