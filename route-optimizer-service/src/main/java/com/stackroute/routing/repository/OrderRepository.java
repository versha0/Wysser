package com.stackroute.routing.repository;

import com.stackroute.routing.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {


    public Order findByOrderId(int orderId);
    public List<Order> findByWholesalerIdAndSlot(int wholesalerId,String slot);


}

