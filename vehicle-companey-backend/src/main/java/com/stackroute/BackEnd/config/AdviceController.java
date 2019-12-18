package com.stackroute.BackEnd.config;

import com.stackroute.BackEnd.exception.VehicleAlreadyExistsException;
import com.stackroute.BackEnd.exception.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.stackroute.BackEnd")
public class AdviceController {

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    public ResponseEntity<Object> myMessage(VehicleAlreadyExistsException e) {
//        System.out.println("inside Message method");
        return new ResponseEntity<>("Global Exception " + e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<Object> myMessage(VehicleNotFoundException e) {
        return new ResponseEntity<>("Global Exception " + e.getMessage(), HttpStatus.CONFLICT);
    }
/*        @ExceptionHandler(Exception.class)
        public ResponseEntity<Object> myMessage(Exception e)
        {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.CONFLICT);
        }*/
}

