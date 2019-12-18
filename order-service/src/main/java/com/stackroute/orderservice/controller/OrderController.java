package com.stackroute.orderservice.controller;

import com.stackroute.orderservice.domain.DateDemand;
import com.stackroute.orderservice.domain.Order;
import com.stackroute.orderservice.domain.TimeSlot;
import com.stackroute.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    ResponseEntity responseEntity;

    @GetMapping("/slots")
    public ResponseEntity<?> getAvailableSlots(@RequestParam("retailerId") int retailerId) {
        try {
            responseEntity = new ResponseEntity<DateDemand>(orderService.checkSlotAvailability(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {

        try {
            //orderService.saveOrder(order);
            responseEntity = new ResponseEntity(orderService.saveOrder(order), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findOrderById(@RequestParam("id") Long id) {

        try {
            responseEntity = new ResponseEntity(orderService.searchOrder(id), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/findByDateAndTimeSlot")
    public ResponseEntity<?> findOrderByDateAndTimeSlot(@RequestParam("date") String date, @RequestParam("timeslot") String timeslot) {

        try {
            responseEntity = new ResponseEntity(orderService.findOrdersByDateAndTimeSlot(date, timeslot), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/findOrdersByStatus")
    public ResponseEntity<?> findOrdersByStatus(@RequestParam("orderStatus") String orderStatus, @RequestParam("retailerId") String retailerId) {

        try {
            responseEntity = new ResponseEntity(orderService.findOrderByStatus(orderStatus, retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findOrdersByStatus() {

        try {
            responseEntity = new ResponseEntity(orderService.getAllOrders(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<?> findOrdersByStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") String orderStatus) {

        try {
            responseEntity = new ResponseEntity(orderService.updateOrderStatus(id, orderStatus), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/findAllOrdersOfRetailer")
    public ResponseEntity<?> findAllOrdersOfRetailer(@RequestParam("retailerId") String retailerId) {

        try {
            responseEntity = new ResponseEntity(orderService.findAllOrdersOfRetailer(retailerId), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
