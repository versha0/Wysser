package com.stackroute.driverdashboardservice.controller;

import com.stackroute.driverdashboardservice.domain.DeliveryRoute;
import com.stackroute.driverdashboardservice.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {
    ResponseEntity responseEntity;
    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/getNextDeliveryRoute")
    public ResponseEntity<?> getAvailableSlots(@RequestParam("date") String date, @RequestParam("vehicleId") String vehicleId, @RequestParam("timeslot") String timeslot) {
        try {
            responseEntity = new ResponseEntity<DeliveryRoute>(deliveryService.getNextDeliveryRoute(date, vehicleId, timeslot), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptDelivery(@RequestBody String vehicleId) {
        try {
            deliveryService.acceptDelivery(vehicleId);
            responseEntity = new ResponseEntity<String>("{\"message\": \"accepted delivery\"}", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectDelivery(@RequestBody String vehicleId) {
        try {
            deliveryService.cancelDelivery(vehicleId);
            responseEntity = new ResponseEntity<String>("{\"message\": \"rejected delivery\"}", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
