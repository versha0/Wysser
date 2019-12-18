package com.stackroute.vehicledemand.repository;

import com.stackroute.vehicledemand.domain.acceptedRetailerRequest;
import com.stackroute.vehicledemand.domain.newRetailerDemand;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AcceptedRetailerDemandRepository extends MongoRepository<acceptedRetailerRequest, BigInteger> {
    public List<acceptedRetailerRequest> findByRetailerIdAndSlot(int retailerId,String slot);
    public acceptedRetailerRequest findByRetailerIdAndVehicleNumber(int retailerId,String vehicleNumber);
    public List<acceptedRetailerRequest> findByRetailerId(int retailerId);
   // public List<acceptedRetailerRequest> findByRetailerIdAndSlot(int retailerId, String slot);
}
