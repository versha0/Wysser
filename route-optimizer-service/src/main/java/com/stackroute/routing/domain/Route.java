package com.stackroute.routing.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Route {
    @Id
    int id;
    String vehicleNumber;
    String slot;
    String depotAddress;
    Float depotLatitude;
    Float depotLongitude;
    int wholesalerId;
//    String date;

    //distance from last node to depot
    double distance;

    String routes;
}
