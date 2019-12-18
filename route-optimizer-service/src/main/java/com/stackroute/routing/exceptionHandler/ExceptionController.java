package com.stackroute.routing.exceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleAnyException(HttpServletRequest request, Exception ex)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
