package com.stackroute.vehicledemand.domain;

public class TimeSlot {

    private String slot;
    private double volume;


    public TimeSlot(String slot, double volume) {
        this.slot = slot;
        this.volume = volume;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
