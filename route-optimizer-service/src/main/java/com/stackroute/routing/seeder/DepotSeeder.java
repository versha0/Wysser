//package com.stackroute.routing.seeder;
//
//import com.stackroute.routing.domain.Depot;
//import com.stackroute.routing.domain.Order;
//import com.stackroute.routing.repository.DepotRepository;
//import com.stackroute.routing.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DepotSeeder  implements CommandLineRunner {
//
//    DepotRepository depotRepository;
//
//    @Autowired
//    public DepotSeeder(DepotRepository depotRepository) {
//        this.depotRepository = depotRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        Depot depot =new Depot();
//        depot.setId(0);
//        depot.setWholesalerId(11);
//        depot.setDepotAddress("koramangala");
//        System.out.println("in seeder");
//        if(depotRepository.findAll()==null)
//        {
//            depotRepository.save(depot);
//            System.out.println("depot seeded");
//        }
//    }
//}
