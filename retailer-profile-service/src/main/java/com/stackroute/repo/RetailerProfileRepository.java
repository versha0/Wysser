package com.stackroute.repo;

import com.stackroute.domain.RetailerProfile;
import com.stackroute.domain.VehicleCompanyProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetailerProfileRepository extends CrudRepository<RetailerProfile, Integer> {

    public RetailerProfile findRetailerProfileByEmail(String email);



}
