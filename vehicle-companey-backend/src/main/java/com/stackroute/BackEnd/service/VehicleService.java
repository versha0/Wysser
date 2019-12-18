package com.stackroute.BackEnd.service;

import com.stackroute.BackEnd.domain.Driver;
import com.stackroute.BackEnd.domain.Vehicle;
import com.stackroute.BackEnd.exception.VehicleAlreadyExistsException;
import com.stackroute.BackEnd.exception.VehicleNotFoundException;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    public Vehicle saveVehicle(Vehicle vehicle) throws VehicleAlreadyExistsException;

    public List<Vehicle> getVehicles();

    public List<Vehicle> getAllVehicles();

    public List<Driver> getAcceptedVehicle(String companyName);

    public List<Driver>getfindByVehicleNumber(String vehicleNumber);

    public List<Driver>getfindByCompanyName(String companyName);

    public List<Vehicle>findByCompanyName(String companyName);

    public List<Driver>getByBookingId(Long bookingId);

    public Vehicle updateComments(Vehicle vehicle);

    public Vehicle updateVehicle(Vehicle vehicle);

    public boolean deleteVehicle(BigInteger vehicleId);

    public Optional<Vehicle> getVehicleById(BigInteger id) throws VehicleNotFoundException;



    //    public List<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) throws VehicleNotFoundException;
    List<Vehicle> getlistbyslot1anddate(int capacity,String slot1);
    List<Vehicle> getlistbyslot2anddate(int  capacity,String slot2);
    List<Vehicle> getlistbyslot3anddate(int  capacity,String slot3);
    List<Vehicle> getVehicleForRetailerRequest(String slot, String date, String vehicleType);
    //     List<Vehicle> getvehiclebyDateSlotandStatus(String date,String slot,String status);
    public Driver saveDriver(Driver driver);
}

