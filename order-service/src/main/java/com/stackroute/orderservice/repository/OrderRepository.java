package com.stackroute.orderservice.repository;

import com.stackroute.orderservice.domain.Order;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends Neo4jRepository<Order, Integer> {

    @Query("CREATE (o:Order{order}) return o;")
    Order saveOrders(@Param("order") Order order);

    @Query("MATCH(o: Order) WHERE ID(o) = {id} RETURN o")
    Order findById(@Param("id") Long id);

    @Query("MATCH (n:Order) RETURN n")
    List<Order> findAllOrders();

    @Query("MATCH (n:Order) WHERE ID(n) = {id} SET n.orderStatus = {status} RETURN n")
    Order updateOrder(@Param("id") Long id, @Param("status") String status);

    List<Order> findByDeliveryDateAndSlotNumber(@Param("deliveryDate") String date, @Param("slotNumber") String slotNumber);

    List<Order> findByOrderStatusAndRetailerId(@Param("orderStatus") String status, @Param("retailerId") String retailerId);

    @Query("MATCH (n:Order) WHERE n.retailerId = {retailerId} RETURN n")
    List<Order> findAllOrdersByRetailerId(@Param("retailerId") String retailerId);
}
