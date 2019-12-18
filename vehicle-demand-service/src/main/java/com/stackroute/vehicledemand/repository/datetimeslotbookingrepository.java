package com.stackroute.vehicledemand.repository;

import com.stackroute.vehicledemand.domain.DateDemand;
import com.stackroute.vehicledemand.domain.retailerdemand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface datetimeslotbookingrepository extends MongoRepository<DateDemand, String> {
}
