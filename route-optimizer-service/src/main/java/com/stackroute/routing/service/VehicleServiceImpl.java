//package com.stackroute.routing.service;
//
//import com.stackroute.routing.domain.Vehicle;
//import com.stackroute.routing.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class VehicleServiceImpl implements VehicleService {
//    VehicleRepository vehicleRepository;
//
//    @Autowired
//    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
//        this.vehicleRepository = vehicleRepository;
//    }
//
//    @Override
//    public Vehicle saveVehicle(Vehicle vehicle) throws Exception {
//        if(vehicleRepository.findByVehicleNumber(vehicle.getVehicleNumber())!=null)
//            throw  new Exception("vehicle already exists");
//        return vehicleRepository.save(vehicle);
//    }
//
//    @Override
//    public Vehicle deleteVehicle(String vehicleNo) throws Exception {
//        Vehicle vehicle =vehicleRepository.findByVehicleNumber(vehicleNo);
//        if(vehicle!=null)
//            vehicleRepository.deleteById(vehicle.getId());
//        else
//            throw new Exception("vehicle not found to be deleted");
//        return vehicleRepository.findByVehicleNumber(vehicleNo);
//    }
//}
