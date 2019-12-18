package com.stackroute.BackEnd.repository;

import com.stackroute.BackEnd.domain.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface DriverRepository extends MongoRepository<Driver, Integer> {
//    Query querrytest= new Query();
//    querrytest.addCriteria();
//
//    @Query("{'capacity': ?0 ,'slot1': ?1}")
//    public List<Vehicle> findbydateandslot1(int capacity,String slot1);
//    @Query("{'capacity': ?0,'slot2': ?1}")
//    public List<Vehicle> findbydateandslot2(int capacity,String slot2);
//    @Query("{'capacity': ?0,'slot2': ?1}")
//    public List<Vehicle> findbydateandslot3(int capacity,String slot3);


    //
//
//
    List<Driver> findByVehicleNumber(String vehicleNumber);
    List<Driver> findByCompanyName(String companyName);
    List<Driver> findByBookingId(long bookingId);
//
//    List<Vehicle> findBySlot1StatusAndDateAndVehicleType(String slot1Status, String date,String vehicleType);
//
//
//    List<Vehicle> findBySlot2StatusAndDateAndVehicleType(String slot2Status, String date,String vehicleType);
//
//    List<Vehicle> findBySlot3StatusAndDateAndVehicleType(String slot3Status, String date,String vehicleType);
}
