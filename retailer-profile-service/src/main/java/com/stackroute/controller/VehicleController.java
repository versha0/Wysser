package com.stackroute.controller;

import com.stackroute.domain.VehicleCompanyProfile;
import com.stackroute.service.VehicleCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/vehicleCompanyProfile")
public class VehicleController {

    VehicleCompanyService vehicleCompanyService;

    @Autowired
    public VehicleController(VehicleCompanyService vehicleCompanyService) {
        this.vehicleCompanyService = vehicleCompanyService;
    }

    @CrossOrigin
        @PostMapping("/saveVehicleCompanyDetail")
    public void saveVehicleCompanyData(@RequestBody VehicleCompanyProfile vehicleCompanyProfile){

        VehicleCompanyProfile existed = vehicleCompanyService.getVehicleCompanyProfileByEmail(vehicleCompanyProfile.getEmail());

        if(existed != null){
            vehicleCompanyProfile.setId(existed.getId());
        }

        vehicleCompanyService.saveData(vehicleCompanyProfile);
    }

    @CrossOrigin
    @GetMapping("/getProfile")
    public VehicleCompanyProfile getCompanyDetailFromEmail(@RequestParam("email") String email){
        return vehicleCompanyService.getVehicleCompanyProfileByEmail(email);
    }

    @CrossOrigin
    @GetMapping("/getAllProfile")
    public List<VehicleCompanyProfile> getAllCompanyDetail(@RequestParam("email") String email){
        return vehicleCompanyService.getAllVehicleCompanyProfile();
    }

    @GetMapping("/findById")
    @CrossOrigin
    public VehicleCompanyProfile getProfileById(@RequestParam("id") int id){
        return vehicleCompanyService.getProfileById(id);
    }



    @DeleteMapping("/deleteAccount")
    @CrossOrigin
    public void deleteHandler(@RequestParam("id") int id ){
        System.out.println("delete this id acc = "+id);

        vehicleCompanyService.deleteAccount(id);
    }

    @GetMapping("/getAddressbyId")
    @CrossOrigin
    public String getAddress(@RequestParam("id") int id){
        System.out.println("vehicleC address of id = "+ id);
        return vehicleCompanyService.getAddressById(id);
    }


}
