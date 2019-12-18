package com.stackroute.BackEnd.repository;

import com.stackroute.BackEnd.domain.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, BigInteger> {
//    Query querrytest= new Query();
//    querrytest.addCriteria();
//
//    @Query("{'capacity': ?0 ,'slot1': ?1}")
//    public List<Vehicle> findbydateandslot1(int capacity,String slot1);
//    @Query("{'capacity': ?0,'slot2': ?1}")
//    public List<Vehicle> findbydateandslot2(int capacity,String slot2);
//    @Query("{'capacity': ?0,'slot2': ?1}")
//    public List<Vehicle> findbydateandslot3(int capacity,String slot3);

    public List<Vehicle> findByCapacityAndSlot1(int capacity, String slot1);
    public List<Vehicle> findByCapacityAndSlot2(int capacity, String slot2);
    public List<Vehicle> findByCapacityAndSlot3(int capacity, String slot3);
    //
//
//

    List<Vehicle> findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findByCompanyName(String companyName);
//    List<Vehicle> findByBookingId(long bookingId);
//
//    List<Vehicle> findBySlot1StatusAndDateAndVehicleType(String slot1Status, String date,String vehicleType);
//
//
//    List<Vehicle> findBySlot2StatusAndDateAndVehicleType(String slot2Status, String date,String vehicleType);
//
//    List<Vehicle> findBySlot3StatusAndDateAndVehicleType(String slot3Status, String date,String vehicleType);
}
