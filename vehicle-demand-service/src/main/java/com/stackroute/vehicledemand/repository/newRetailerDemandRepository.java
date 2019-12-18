package com.stackroute.vehicledemand.repository;

import com.stackroute.vehicledemand.domain.newRetailerDemand;
import com.stackroute.vehicledemand.domain.retailerdemand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;

@Repository
public interface newRetailerDemandRepository extends MongoRepository<newRetailerDemand, BigInteger> {
   public List<newRetailerDemand> findByRetailerId(int retailerId);
   public List<newRetailerDemand> findByCompanyName(String companyName);
}
