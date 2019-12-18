package com.stackroute.vehicledemand.repository;

import com.stackroute.vehicledemand.domain.acceptedRetailerRequest;
import com.stackroute.vehicledemand.domain.newRetailerDemand;
import com.stackroute.vehicledemand.domain.rejectedRetailerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface rejectedRetailerDemand extends MongoRepository<rejectedRetailerRequest, BigInteger> {
    public List<rejectedRetailerRequest> findByRetailerId(int retailerId);
}
