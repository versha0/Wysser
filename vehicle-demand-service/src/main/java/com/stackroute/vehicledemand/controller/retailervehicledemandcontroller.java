package com.stackroute.vehicledemand.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.stackroute.vehicledemand.domain.*;
import com.stackroute.vehicledemand.repository.vehicledemandrepository;
import com.stackroute.vehicledemand.service.vehicledemandservice;
//import netscape.javascript.JSObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Properties;

@RestController
@CrossOrigin("*")

public class retailervehicledemandcontroller {
    //Kafka Properties
    @Value("${kafka.bootstrap.servers}")
    private String kafkaBootstrapServers;
    private Properties producerProperties;
    private KafkaProducer<String, String> producer;
    @Value("${kafka.topic.vehicle_slots}")
    private String vehicleSlotsTopicName;

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

    private static void sendKafkaMessage(String payload,
                                         KafkaProducer<String, String> producer,
                                         String topic) {
        System.out.println("Sending Kafka message: " + payload);
        producer.send(new ProducerRecord<>(topic, payload));
    }

    @Autowired
    public vehicledemandservice vehicledemandservice;
//    @Autowired
//    public retailervehicledemandcontroller(vehicledemandservice vehicledemandservice){
//        this.vehicledemandservice=vehicledemandservice;
//    }


    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity savenewretailerdemand(@RequestBody retailerdemand retailerdemand) {
//        retailerdemand x = new retailerdemand();
//        x = retailerdemand;
//        System.out.println(x.getTimeSlot());
//        System.out.println(x.getDate());
//        System.out.println(x.getVolume());

        ResponseEntity responseEntity;
        try {
            vehicledemandservice.savenewvehicledemand(retailerdemand);
            //Hard coded to get dateDemand
            DateDemand[] dateDemandsDummy = {new DateDemand(
                    "2019-12-01", new TimeSlot("11:00-13:00", 100), new TimeSlot("14:00-16:00", 20), new TimeSlot("17:00-19:00", 50)
            )};
            toJson(dateDemandsDummy);
            responseEntity = new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("get some sense", HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }


    public void toJson(DateDemand[] dateDemands) {
        //send to kafka topic
        Gson gson = new Gson();
        String json = gson.toJson(dateDemands);
        assignProducerProperties();
        sendKafkaMessage(json, producer, vehicleSlotsTopicName);
        //return json;
    }
    @PostMapping("/savenewdemand")
    public ResponseEntity<?> savenewDemand(@RequestBody newRetailerDemand newRetailerDemand) {
        System.out.println("test");
        ResponseEntity responseEntity;
        try {
            //orderService.saveOrder(order);
            responseEntity = new ResponseEntity(vehicledemandservice.savenewvehicledemandbyretailer(newRetailerDemand), HttpStatus.CREATED);
            System.out.print(newRetailerDemand.toString());
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("/findAll/{companyName}")
    public ResponseEntity<?> findallrequested(@PathVariable("companyName") String companyName) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.getallvehicledemanded(companyName), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping(value = "deletedemand/{id}")
    public ResponseEntity<BigInteger> deletePost(@PathVariable BigInteger id) {

        boolean isRemoved = vehicledemandservice.deletebyId(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<BigInteger>(id, HttpStatus.OK);
    }
    @PostMapping("/saveacceptedstatusdemand")
    public ResponseEntity savenewDemand(@RequestBody acceptedRetailerRequest acceptedRetailerRequest) {
        System.out.println("test");
        ResponseEntity responseEntity;
        try {
            //orderService.saveOrder(order);
            responseEntity = new ResponseEntity<>(vehicledemandservice.savenewaccepetedVehicleDemand(acceptedRetailerRequest), HttpStatus.CREATED);
            System.out.print(acceptedRetailerRequest.toString());
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @PostMapping("/saverejectedstatusdemand")
    public ResponseEntity savenewDemand(@RequestBody rejectedRetailerRequest rejectedRetailerRequest) {
        System.out.println("test");
        ResponseEntity responseEntity;
        try {
            //orderService.saveOrder(order);
            responseEntity = new ResponseEntity<>(vehicledemandservice.savenewrejectedVehicleDemand(rejectedRetailerRequest), HttpStatus.CREATED);
            System.out.print(rejectedRetailerRequest.toString());
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping(value = "searchbyretailerid/{retailerId}")
    public ResponseEntity<BigInteger> searchbyretailerId(@PathVariable int retailerId) {

//        this.vehicledemandservice.searchbyretailerId(retailerId);


        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.searchbyretailerId(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping(value = "searchbyretaileridinrejected/{retailerId}")
    public ResponseEntity<BigInteger> searchbyretailerIdinrejected(@PathVariable int retailerId) {

//        this.vehicledemandservice.searchbyretailerId(retailerId);


        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.findByRetailerIdinrejectedlist(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping(value = "updateremainingvolume/{retailerId}/{slot}/{volumeBooked}")
    public ResponseEntity<BigInteger> searchbyretailerIdinrejected(@PathVariable("retailerId") int retailerId, @PathVariable("slot") String slot,@PathVariable("volumeBooked") int volumeBooked) {

//        this.vehicledemandservice.searchbyretailerId(retailerId);


        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.updateremainingvolumeinvehicle(retailerId,slot,volumeBooked), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping(value = "searchbyretaileridinaccepted/{retailerId}")
    public ResponseEntity<BigInteger> searchbyretailerIdinaccepted(@PathVariable int retailerId) {

//        this.vehicledemandservice.searchbyretailerId(retailerId);


        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.findByRetailerIdinacceptedlist(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping(value = "searchByRetailerIdAndSlot/{retailerId}/{slot}")
    public ResponseEntity<BigInteger> searchbyretaileridandslot(@PathVariable("retailerId") int retailerId, @PathVariable("slot") String slot) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.findByRetailerIdAndSlot(retailerId, slot), HttpStatus.OK);
            Gson gson = new Gson();
            String json = gson.toJson(responseEntity.getBody());
            assignProducerProperties();
            sendKafkaMessage(json, producer, vehicleSlotsTopicName);

        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping(value = "searchByRetailerIdForOrder/{retailerId}")
    public ResponseEntity<BigInteger> searchbyretaileridandslot(@PathVariable("retailerId") int retailerId) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(vehicledemandservice.findByRetailerIdForOrder(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
