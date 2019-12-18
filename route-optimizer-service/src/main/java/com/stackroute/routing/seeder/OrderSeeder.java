package com.stackroute.routing.seeder;

import com.stackroute.routing.domain.Order;
import com.stackroute.routing.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSeeder  implements CommandLineRunner {
    OrderRepository orderRepository;

    @Autowired
    public OrderSeeder(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setId(0);
        order.setWholesalerId(11);
        order.setCustomerAddress("marathahalli");
        order.setOrderId(2);
        order.setOrderVolume(20);
        order.setSlot("1");

        order.setOrderStatus("free");

        if(orderRepository.findAll()==null)
        orderRepository.save(order);
    }
}
