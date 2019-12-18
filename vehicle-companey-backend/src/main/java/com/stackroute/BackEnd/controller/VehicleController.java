package com.stackroute.BackEnd.controller;

import com.google.gson.Gson;
import com.stackroute.BackEnd.domain.Driver;
import com.stackroute.BackEnd.domain.RetailerDemand;
import com.stackroute.BackEnd.domain.Vehicle;
import com.stackroute.BackEnd.exception.VehicleAlreadyExistsException;
import com.stackroute.BackEnd.exception.VehicleNotFoundException;
import com.stackroute.BackEnd.repository.DriverRepository;
import com.stackroute.BackEnd.repository.VehicleRepository;
import com.stackroute.BackEnd.service.VehicleService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@RestController
//@CrossOrigin("*")
@RequestMapping(value = "/api/v1")
public class VehicleController<VehicleDao> {
    //Actual Kafka


    @Value("${kafka.bootstrap.servers}")
    private String kafkaBootstrapServers;
    private Properties producerProperties;
    private KafkaProducer<String, String> producer;
    @Value("${kafka.topic.book_driver}")
    private String bookDriverTopic;

    public DriverRepository driverRepository;
    //method to send messages
    private static void sendKafkaMessage(String payload,
                                         KafkaProducer<String, String> producer,
                                         String topic) {
        System.out.println("Sending Kafka message: " + payload);
        producer.send(new ProducerRecord<>(topic, payload));
    }

    private void assignProducerProperties() {
        /*
         * Defining producer properties.
         */
        producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", kafkaBootstrapServers);
        producerProperties.put("acks", "all");
        producerProperties.put("retries", 0);
        producerProperties.put("batch.size", 16384);
        producerProperties.put("linger.ms", 1);
        producerProperties.put("buffer.memory", 33554432);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(producerProperties);
    }

    VehicleService vehicleService;

    @Autowired
    public VehicleController(DriverRepository driverRepository, VehicleService vehicleService) {
        this.driverRepository=driverRepository;
        this.vehicleService = vehicleService;
    }

    @PostMapping("vehicle")

    @CrossOrigin
    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("id = "+vehicle.getId());
        System.out.println(vehicle.toString());

        ResponseEntity responseEntity;

        vehicleService.saveVehicle(vehicle);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }

//...............Save Accepted Vehicle..............................................


    @PostMapping("AcceptedVehicle")

    @CrossOrigin
    public ResponseEntity<?> saveAcceptedVehicle(@RequestBody Driver driver) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("Accepted Vehicle id = "+driver.getId());
        System.out.println(driver.toString());
        Driver driver1=driver;

        List<Driver> drivers=driverRepository.findAll();
        Iterator<Driver> it =drivers.iterator();
        Long id=0L;
        while (it.hasNext())
        {
            id=it.next().getBookingId();
        }
        id++;
        driver1.setBookingId(id);

        ResponseEntity responseEntity;

        vehicleService.saveDriver(driver1);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }

//....................get Accepted Vehicle By company name.......................,,,,,

    @GetMapping("AcceptedVehicle/{companyName}")
    @CrossOrigin
    public ResponseEntity<?> getAcceptedVehicle(@PathVariable("companyName") String companyName) {
        ResponseEntity responseEntity;
        List<Driver> vehicles = vehicleService.getAcceptedVehicle(companyName);

        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);

        return responseEntity;
    }





    @GetMapping("findByVehicleNumber/{vehicleNumber}")
    @CrossOrigin
    public ResponseEntity<?> getfindByVehicleNumber(@PathVariable("vehicleNumber") String vehicleNumber) {
        ResponseEntity responseEntity;
        List<Driver> vehicles = vehicleService.getfindByVehicleNumber(vehicleNumber);

        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);

        return responseEntity;
    }



    @GetMapping("findByCompanyName/{companyName}")
    @CrossOrigin
    public ResponseEntity<?> getfindByCompanyName(@PathVariable("companyName") String companyName) {
        ResponseEntity responseEntity;
        List<Driver> vehicles = vehicleService.getfindByCompanyName(companyName);

        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);

        return responseEntity;
    }


    @GetMapping("BookingId/{bookingId}")
    @CrossOrigin
    public ResponseEntity<?> getByBookingId(@PathVariable("bookingId") long bookingId) {
        ResponseEntity responseEntity;
        List<Driver> vehicles = vehicleService.getByBookingId(bookingId);

        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);

        return responseEntity;
    }


//.......................Save Rejected Vehicle.........................


    @PostMapping("RejectedVehicle")

    @CrossOrigin
    public ResponseEntity<?> saveRejectedVehicle(@RequestBody Vehicle vehicle) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("Rejected Vehicle id = "+vehicle.getId());
        System.out.println(vehicle.toString());

        ResponseEntity responseEntity;

        vehicleService.saveVehicle(vehicle);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        return responseEntity;
    }



    @PostMapping("Accept")

    @CrossOrigin
    public ResponseEntity<?> SendAcceptedVehicle(@RequestBody RetailerDemand retailerDemand) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("id = "+retailerDemand.getId());
        System.out.println(retailerDemand.toString());
        ResponseEntity responseEntity;
        try {
            Vehicle vehicle = vehicleService.getVehicleById(retailerDemand.getId()).get();
            resetVehicleStatus(vehicle.getId(), retailerDemand.getSlot());
            //vehicleService.saveVehicle(vehicle);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }catch(Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @PostMapping("Reject")

    @CrossOrigin
    public ResponseEntity<?> SendRejectedVehicle(@RequestBody Vehicle vehicle) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("id = "+vehicle.getId());
        System.out.println(vehicle.toString());
        ResponseEntity responseEntity;
        vehicleService.saveVehicle(vehicle);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        return responseEntity;
    }




    @PostMapping("resetStatus/{id}/{slot}")
    @CrossOrigin
    public ResponseEntity<?> resetVehicleStatus(@PathVariable("id") BigInteger id, @PathVariable("slot") String slot) throws VehicleAlreadyExistsException {

        System.out.println("values");

        System.out.println("id = " + id);
        System.out.println("slot = " + slot);
//        System.out.println(vehicle.toString());
        ResponseEntity responseEntity;
        try {
            String currentStatus = "";
            Vehicle savedVehicle = vehicleService.getVehicleById(id).get();
            if(slot.equals("slot1")){
                currentStatus = savedVehicle.getSlot1();
                if (currentStatus.equals("Available")){
                    savedVehicle.setSlot1("Not Available");
                }else{
                    savedVehicle.setSlot1("Available");
                }
            }
            else if(slot.equals("slot2")){
                System.out.println("inside slot2");
                currentStatus = savedVehicle.getSlot2();
                if (currentStatus.equals("Available")){
                    savedVehicle.setSlot2("Not Available");
                }else{
                    savedVehicle.setSlot2("Available");
                }
            }
            else if(slot.equals("slot3")){
                currentStatus = savedVehicle.getSlot3();
                if (currentStatus.equals("Available")){
                    savedVehicle.setSlot3("Not Available");
                }else{
                    savedVehicle.setSlot3("Available");
                }
            }

            responseEntity = new ResponseEntity<Vehicle>(vehicleService.updateVehicle(savedVehicle), HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

//        vehicleService.saveVehicle(vehicle);
//        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        return responseEntity;
    }




    @GetMapping("findVehicles/{companyName}")
    @CrossOrigin
    public ResponseEntity<?> getVehicles(@PathVariable("companyName") String companyName) {
        ResponseEntity responseEntity;

        List<Vehicle> vehicles = vehicleService.findByCompanyName(companyName);
        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("vehicles")
    @CrossOrigin
    public ResponseEntity<?> getAllVehicles() {
        ResponseEntity responseEntity;
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        //try{
        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);
        //}catch (Exception ex) {
        //  responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }


    @GetMapping("vehicle/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable BigInteger id) throws VehicleNotFoundException {
        ResponseEntity responseEntity;
        // try{
        responseEntity = new ResponseEntity<Optional<Vehicle>>(vehicleService.getVehicleById(id), HttpStatus.CREATED);
        //}catch (TrackNotFoundException ex) {
        //responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }

//    @GetMapping("vehicle/vehicleNumber")
//    public ResponseEntity<?> getVehicleByVehicleNumber(@PathVariable(value = "vehicleNumber") String vehicleNumber) throws VehicleNotFoundException {
//        ResponseEntity responseEntity;
//        // try{
//        vehicleService.getVehicleByVehicleNumber(vehicleNumber);
//        responseEntity = new ResponseEntity<List<Vehicle>>(vehicleService.getVehicleByVehicleNumber(vehicleNumber), HttpStatus.OK);
//        // }catch (TrackNotFoundException ex) {
//        //   responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        //}
//        return responseEntity;
//    }


    @PutMapping("vehicle/{id}")
    public  ResponseEntity<?> updateVehicles(@PathVariable(value = "id") BigInteger id,@Valid @RequestBody Vehicle vehicle) throws VehicleNotFoundException, VehicleAlreadyExistsException {
        ResponseEntity responseEntity;
        Optional<Vehicle> vehicle1 = vehicleService.getVehicleById(id);
        try{
            if(!vehicle1.isPresent()){
                throw new Exception("id-"+id);
            }
            vehicle.setId(id);
            vehicleService.saveVehicle(vehicle);
            responseEntity = new ResponseEntity(vehicleService.getVehicles(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("vehicle/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteVehicles(@PathVariable("id") BigInteger id) {

        ResponseEntity responseEntity;
        //try{
        vehicleService.deleteVehicle(id);
        responseEntity = new ResponseEntity(vehicleService.getVehicles(), HttpStatus.CREATED);
        //}catch (Exception ex) {
        //  responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }


    List<Vehicle> vehiclesForDriverDashboard;

    @GetMapping("/BookVehicle")
    @CrossOrigin
    public ResponseEntity<?> bookVehicleHandler(@RequestParam("slot") String slot, @RequestParam("date") String date, @RequestParam("type") String vehicleType ){

        List<Vehicle> vehicleList = vehicleService.getVehicleForRetailerRequest(slot,date,vehicleType);

        System.out.printf("one");

        this.vehiclesForDriverDashboard = vehicleList;
        Vehicle[] vehicleArray = new Vehicle[vehiclesForDriverDashboard.size()];
        for (int i =0; i < vehiclesForDriverDashboard.size(); i++) {
            vehicleArray[i] = vehiclesForDriverDashboard.get(i);
        }
        // if(vehicleArray.length > 0){
        //     Gson gson = new Gson();
        //     sendKafkaMessage(gson.toJson(vehicleArray), producer, bookDriverTopic);
        // }
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity(vehicleList, HttpStatus.OK);

        return responseEntity;
    }
    //
//
    @GetMapping(value="queryslot1/{capacity}/{slot1}")
    @CrossOrigin
    public ResponseEntity<?> getVehiclesforslot1anddate(@PathVariable("capacity") int capacity, @PathVariable("slot1") String slot1) {
        ResponseEntity responseEntity;
        System.out.print(capacity);
        System.out.print(slot1);

        List<Vehicle> vehicles = vehicleService.getlistbyslot1anddate(capacity,"Available");
        System.out.print(vehicles);
        //try{
        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);
        //}catch (Exception ex) {
        //  responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }
    @GetMapping(value="queryslot2/{capacity}/{slot2}")
    @CrossOrigin
    public ResponseEntity<?> getVehiclesforslot2anddate(@PathVariable("capacity") int capacity, @PathVariable("slot2") String slot2) {
        ResponseEntity responseEntity;
        System.out.print(capacity);
        System.out.print(slot2);
        List<Vehicle> vehicles = vehicleService.getlistbyslot2anddate(capacity,"Available");
        System.out.print(vehicles);
        //try{
        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);
        //}catch (Exception ex) {
        //  responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }
    @GetMapping(value="queryslot3/{capacity}/{slot3}")
    @CrossOrigin
    public ResponseEntity<?> getVehiclesforslot3anddate(@PathVariable("capacity") int capacity, @PathVariable("slot3") String slot3) {
        ResponseEntity responseEntity;
        System.out.print(capacity);
        System.out.print(slot3);
        List<Vehicle> vehicles = vehicleService.getlistbyslot3anddate(capacity, "Available");
        System.out.print(vehicles);
        //try{
        responseEntity = new ResponseEntity<>(vehicles, HttpStatus.OK);
        //}catch (Exception ex) {
        //  responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        //}
        return responseEntity;
    }
}

