package com.stackroute.service;

import com.google.gson.Gson;
import com.stackroute.domain.RetailerProfile;
import com.stackroute.domain.VehicleCompanyProfile;
import com.stackroute.repo.RetailerProfileRepository;
import com.stackroute.repo.VehicleCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCompanyService {

private VehicleCompanyRepository vehicleCompanyRepository;

    @Autowired
    public VehicleCompanyService(VehicleCompanyRepository vehicleCompanyRepository) {
        this.vehicleCompanyRepository = vehicleCompanyRepository;
    }

    public void saveData(VehicleCompanyProfile vehicleCompanyProfile){

       vehicleCompanyRepository.save(vehicleCompanyProfile);
    }

    public VehicleCompanyProfile getVehicleCompanyProfileByEmail(String email){
       return vehicleCompanyRepository.findVehicleCompanyProfileByEmail(email);
    }


    public List<VehicleCompanyProfile> getAllVehicleCompanyProfile() {
        return (List<VehicleCompanyProfile>)vehicleCompanyRepository.findAll();
    }

    public VehicleCompanyProfile getProfileById(int id){
        return vehicleCompanyRepository.findById(id).get();
    }

    public void deleteAccount(int id){
        vehicleCompanyRepository.deleteById(id);
    }


    public String getAddressById(int id) {

        VehicleCompanyProfile vehicleCompanyProfile= vehicleCompanyRepository.findById(id).get();

        if(vehicleCompanyProfile != null){
            return vehicleCompanyProfile.getAddress();
        }
        else {
            return "Address Not Exist";
        }

    }
}
