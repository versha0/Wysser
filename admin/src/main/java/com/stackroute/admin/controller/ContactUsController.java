package com.stackroute.admin.controller;

import com.stackroute.admin.domain.ContactUs;
import com.stackroute.admin.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ContactUsController {

    @Autowired
    ContactUsService contactUsService;


//    @PostMapping("/savenewcontactusrequest")
            @PostMapping("/savenewcontactusrequest")
            public ResponseEntity savenewContactUsRequest(@RequestBody ContactUs contactUs) {
            System.out.println("test");
            ResponseEntity responseEntity;
            try {
                //orderService.saveOrder(order);
                responseEntity = new ResponseEntity<>(contactUsService.saveNewContactUSRequest(contactUs), HttpStatus.CREATED);
//                System.out.print(rejectedRetailerRequest.toString());
            } catch (Exception e) {
                responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
            return responseEntity;
            }
    @GetMapping("/getNotreplied")
    public ResponseEntity<?> getnotreplied() {
                System.out.println("in get not replied");
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity(contactUsService.getbyReplySttaus(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
