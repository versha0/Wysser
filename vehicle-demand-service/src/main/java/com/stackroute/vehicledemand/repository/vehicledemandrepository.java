package com.stackroute.vehicledemand.repository;

import com.stackroute.vehicledemand.domain.retailerdemand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface vehicledemandrepository extends MongoRepository<retailerdemand, String> {
}
