package com.stackroute.routing.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;


@Document
@Data
public class Vehicle {

    @Id
    BigInteger id;
    String vehicleNumber;
    String driverName;
    String vehicleType;
    int capacity;
    int costPerSlot;
    String vehicleStatus;
    String slot1;
    String slot2;
    String slot3;
//    String date;

    String companyName;
    String slot;
    int retailerId;
    String requestStatus;
    int remaningCapacity;
}
