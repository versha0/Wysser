package com.stackroute.routing.service;

import com.stackroute.routing.domain.Order;
import com.stackroute.routing.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {


    public Order addOrder(Order order) throws Exception;

    public List<Order> getAllOrders() throws Exception;

    public Order deleteOrder(int orderId) throws Exception;

}
