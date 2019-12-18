package com.stackroute.routing.repository;

import com.stackroute.routing.domain.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends CrudRepository<Route,Integer> {

    public List<Route> findByVehicleNumberAndSlot(String vehicleNumber,String slot);
    public List<Route> findByWholesalerIdAndSlot(int wholesalerId, String slot);
}
