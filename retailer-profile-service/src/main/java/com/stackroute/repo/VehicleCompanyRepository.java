package com.stackroute.repo;

import com.stackroute.domain.VehicleCompanyProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCompanyRepository extends CrudRepository<VehicleCompanyProfile,Integer> {

    public VehicleCompanyProfile findVehicleCompanyProfileByEmail(String email);

}
