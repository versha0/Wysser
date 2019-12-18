package com.stackroute.signaturebackend.controller;

import com.stackroute.signaturebackend.domain.signature;
import com.stackroute.signaturebackend.service.signatureservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Signature;

@RestController
@CrossOrigin("*")
@RequestMapping("/")

public class signaturecontroller {
    @Autowired
    public signatureservice signatureservice;

    @PostMapping(value = "save", consumes = "application/json")
    public ResponseEntity savenewretailerdemand(@RequestBody signature signature) {
        ResponseEntity responseEntity;
        try {
//            signature signature1 = new signature();
//            signature1.getSignatureimage();
            signature signature1=signatureservice.savenewsignature(signature);
//            System.out.println(signature1.getSignatureimage()+ signature1.getOrderid());
            responseEntity = new ResponseEntity<>("Successfully Created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("get some sense", HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;

    }
}
