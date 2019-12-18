//package com.stackroute.routing.seeder;
//
//import com.stackroute.routing.domain.Order;
//import com.stackroute.routing.domain.Vehicle;
//import com.stackroute.routing.repository.OrderRepository;
//import com.stackroute.routing.repository.VehicleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class VehicleSeeder  implements CommandLineRunner {
//    VehicleRepository vehicleRepository;
//
//    @Autowired
//    public VehicleSeeder(VehicleRepository vehicleRepository) {
//        this.vehicleRepository = vehicleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Vehicle vehicle=new Vehicle();
//        vehicle.setId(0);
//        vehicle.setVehicleNumber("22");
//        vehicle.setCapacity(50);
//
//        if(vehicleRepository.findAll()==null)
//        vehicleRepository.save(vehicle);
//    }
//}
