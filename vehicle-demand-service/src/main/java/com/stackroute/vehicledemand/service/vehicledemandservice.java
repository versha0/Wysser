package com.stackroute.vehicledemand.service;

import com.stackroute.vehicledemand.domain.acceptedRetailerRequest;
import com.stackroute.vehicledemand.domain.newRetailerDemand;
import com.stackroute.vehicledemand.domain.rejectedRetailerRequest;
import com.stackroute.vehicledemand.domain.retailerdemand;
import com.stackroute.vehicledemand.domain.DateDemand;
import com.stackroute.vehicledemand.domain.TimeSlot;

import java.math.BigInteger;
import java.util.List;

public interface vehicledemandservice {
    public retailerdemand savenewvehicledemand(retailerdemand retailerdemand);

    public List<newRetailerDemand> getvehicledemand();
    public newRetailerDemand savenewvehicledemandbyretailer(newRetailerDemand newRetailerDemand);
    public List<newRetailerDemand> getallvehicledemanded(String companyName);
    public boolean deletebyId(BigInteger Id);
    public acceptedRetailerRequest savenewaccepetedVehicleDemand( acceptedRetailerRequest acceptedRetailerRequest);
    public rejectedRetailerRequest savenewrejectedVehicleDemand(rejectedRetailerRequest rejectedRetailerRequest);
    public List<newRetailerDemand> searchbyretailerId(int id);
    public List<rejectedRetailerRequest> findByRetailerIdinrejectedlist(int retailerId);


    public List<acceptedRetailerRequest> updateremainingvolumeinvehicle(int retailerId,String slot,int volumeBooked);

    public List<acceptedRetailerRequest> findByRetailerIdinacceptedlist(int retailerId);


   // public List<acceptedRetailerRequest> addordertoSlotvehicle(int retailerId,String slot,int volumeBooked);
//    public List<acceptedRetailerRequest> findByRetailerIdinacceptedlist(int retailerId);
    public List<acceptedRetailerRequest> findByRetailerIdAndSlot(int retailerId, String slot);
    public DateDemand[] findByRetailerIdForOrder(int retailerId);
}
