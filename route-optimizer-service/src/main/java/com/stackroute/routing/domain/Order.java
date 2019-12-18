package com.stackroute.routing.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Document
@Data
public class Order {


    @Id
    private int id ;
    private int wholesalerId;
    private int orderId ;
//    private String customerName;
    private String customerAddress;
//    private String customerPhoneNumber;
    private String deliveryDate;
    private String slot;
    private long orderVolume;
    private String orderStatus;

//    public String toString()
//    {
////        String
//        return null;
//    }

}

