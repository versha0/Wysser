package com.stackroute.routing.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Depot {

    @Id
    int id;
    int wholesalerId;
    String depotAddress;
}
