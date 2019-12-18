package com.stackroute.orderservice.service;

import com.stackroute.orderservice.domain.DateDemand;
import com.stackroute.orderservice.domain.Order;
import com.stackroute.orderservice.domain.TimeSlot;
import org.json.simple.parser.ParseException;
import com.stackroute.orderservice.domain.VehicleDemanded;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface OrderService {
    Order searchOrder(Long id);

    List<Order> getAllOrders();

    //Should produce to active_orders topic
    Order saveOrder(Order order);

    //Should consume from time_slots topic
    DateDemand checkSlotAvailability(int retailerId) throws ParseException;

    List<Order> findOrdersByDateAndTimeSlot(String date, String timeslot);

    List<Order> findOrderByStatus(String orderStatus, String retailerId);

    Order updateOrderStatus(Long id, String orderStatus);

    List<Order> findAllOrdersOfRetailer(String retailerId);
}
