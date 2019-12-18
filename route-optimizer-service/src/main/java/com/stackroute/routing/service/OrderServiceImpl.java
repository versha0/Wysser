package com.stackroute.routing.service;

import com.google.gson.Gson;
import com.stackroute.routing.domain.Order;
import com.stackroute.routing.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        if(orderRepository.findAll()==null)
            throw new Exception("No orders found");
        List<Order> orders=new ArrayList<>();
        Iterator<Order> it = orderRepository.findAll().iterator();
        while ((it.hasNext()))
        {
            orders.add(it.next());
        }
        return  orders;
    }

    @Override
    public Order addOrder(Order order) throws Exception {
        if(orderRepository.findByOrderId(order.getOrderId())!=null)
            throw  new Exception("order already exists");
        Order order1=orderRepository.save(order);
        if(order1==null)
        {
            System.out.println("order not saved");
        }
        else {
            Gson gson = new Gson();

            System.out.println("saved order:" + (gson.toJson(order1)).toString());
        }
        return  order1;

    }

    @Override
    public Order deleteOrder(int orderId) throws Exception {

        System.out.println("in order");

        Order order =orderRepository.findByOrderId(orderId);
        if(order!=null) {
            System.out.println("in order");
            orderRepository.delete(order);
        }
        else
            throw new Exception("record not found to be deleted");

        return orderRepository.findByOrderId(orderId);
    }
}
