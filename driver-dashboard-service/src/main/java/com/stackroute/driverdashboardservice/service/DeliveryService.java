package com.stackroute.driverdashboardservice.service;

import com.stackroute.driverdashboardservice.domain.DeliveryRoute;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

public interface DeliveryService {
    DeliveryRoute getNextDeliveryRoute(String date, String vehicleId, String timeslot) throws ParseException;

    void cancelDelivery(String vehicleId);

    void acceptDelivery(String vehicleId);
}
