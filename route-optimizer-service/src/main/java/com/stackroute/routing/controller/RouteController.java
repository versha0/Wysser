package com.stackroute.routing.controller;


import com.google.gson.Gson;
import com.stackroute.routing.domain.Depot;
import com.stackroute.routing.domain.Order;
import com.stackroute.routing.domain.Route;
import com.stackroute.routing.domain.Vehicle;
//import com.stackroute.routing.service.DepotService;
import com.stackroute.routing.service.OrderService;
import com.stackroute.routing.service.RouteService;
//import com.stackroute.routing.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value="api/v1")
public class RouteController {
    OrderService orderService;
//    VehicleService vehicleService;
    RouteService routeService;
//    DepotService depotService;
    String vehicleJsonString = "";
//listeners
    @KafkaListener(topics = "vehicle_slots", groupId = "foo")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
        // Gson gson = new Gson();
        // vehicleJsonString = gso  `n.fromJson(message, String.class);
        vehicleJsonString = message;
        System.out.println(vehicleJsonString);
}
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

//    @Autowired
//    public void setVehicleService(VehicleService vehicleService) {
//        this.vehicleService = vehicleService;
//    }

    @Autowired
    public void setRouteService(RouteService routeService) {
        this.routeService = routeService;
    }

//    @Autowired
//    public void setDepotService(DepotService depotService) {
//        this.depotService = depotService;
//    }


    @PostMapping("order")
    public String saveOrder(@RequestBody Order order) throws Exception
    {
        Order order1 =orderService.addOrder(order);
        int wholesalerId=order1.getWholesalerId();
        String slot =order1.getSlot();
        //run route optimizer service
        //use the vehicle demand service to get booked vehicles in current slot of retailer
        //retailer id and timeslot are in order
        String url = "http://15.206.105.26:9090/searchByRetailerIdAndSlot/"+wholesalerId+"/"+slot;
        RestTemplate restTemplate = new RestTemplate();
//        String vehicleJsonString = restTemplate.getForObject(url,String.class);
//        System.out.println(vehicleJsonString);
//        String vehicleJsonString1="[{\"_id\":0,\"vehicleNumber\":\"22\",\"capacity\":50},{\"_id\":45,\"vehicleNumber\":\"343\",\"capacity\":100}]";


        JSONArray vehicleJson = new JSONArray(vehicleJsonString);
//        url="http://15.206.105.26:8082/retailerProfile/getAddressbyId?id="+wholesalerId;
//        JSONObject depot = restTemplate.getForObject(url,JSONObject.class);
//        System.out.println(depot.toString());
//        String depotAddress =depot.getString("address");
//        System.out.println(depotAddress);
        String depotAddress="koramangala";


        JSONObject newRoutes=new JSONObject( routeService.getRoutes(slot,vehicleJson,depotAddress,wholesalerId));

        routeService.saveRoute(newRoutes,wholesalerId,slot);
        return  newRoutes.toString();


        //http://localhost:9090/searchByRetailerIdAndSlot/{retailerId}/{slot}
        //returns all available vehicles
        //format available in vehicle-demand-service-->domain--->AcceptedRetailerDemand
        //get retailer details using retailerId using retailer profile service
        //use to get address 8082/ getRetailerById
        //optimize and save to your database
        //endpoint to access saved routes, based on retailerId and slot
    }

    @GetMapping("order")
    public ResponseEntity<?> getOrders() throws  Exception
    {
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(),HttpStatus.FOUND);

    }

//    @PostMapping("vehicle")
//    public ResponseEntity<?> saveVehicle(@RequestBody Vehicle vehicle) throws Exception
//    {
//        vehicleService.saveVehicle(vehicle);
//        return new ResponseEntity<String>("vehicle added", HttpStatus.OK);
//    }
//    @PostMapping("depot")
//    public ResponseEntity<?> saveDepot(@RequestBody Depot depot) throws Exception
//    {
//        depotService.addDepot(depot);
//        return new ResponseEntity<String>("depot added", HttpStatus.OK);
//    }


    @DeleteMapping("order/{id}")
    public ResponseEntity<?>deleteOrder(@PathVariable int id) throws Exception
    {
        System.out.println("here");
        orderService.deleteOrder(id);
        return new ResponseEntity<String>("{\"message\": \"order deleted\"}", HttpStatus.OK);
    }


//    @DeleteMapping("vehicle/{no}")
//    public ResponseEntity<?>deleteVehicle(@PathVariable String no) throws Exception
//    {
//        vehicleService.deleteVehicle(no);
//        return new ResponseEntity<String>("vehicle deleted", HttpStatus.OK);
//    }
//    @DeleteMapping("depot/{id}")
//    public ResponseEntity<?>deleteDepot(@PathVariable int id) throws Exception
//    {
//        depotService.deleteDepot(id);
//        return new ResponseEntity<String>("depot deleted", HttpStatus.OK);
//    }


    @GetMapping("routes")
    public List<Route> getAllRoutes()
    {
        return routeService.getAllRoutes();
    }
    @GetMapping("routes/{vehicleNumber}/{slot}")
    public String getRoutesByVehicleNoAndSlot(@PathVariable String vehicleNumber,@PathVariable String slot) throws  Exception
    {
        return routeService.getRoutesByVehicle(vehicleNumber,slot);

    }

    @GetMapping("routesBySaler/{wholsalerId}/{slot}")
    public String getRoutesByWholesalerIdAndSlot(@PathVariable int wholsalerId,@PathVariable String slot) throws  Exception
    {
        return routeService.getRoutesByWholesaler(wholsalerId,slot);

    }
    @GetMapping("getcoordinateresponse/{address}")
    public String getCooordinateResponse(@PathVariable String address) throws IOException {
        return routeService.getCoordinateResponse(address).toString();
        // ResponseEntity<JSONObject> responseEntity = new ResponseEntity<JSONObject>(routeService.getCoordinateResponse(address),HttpStatus.OK);
        // System.out.println("\n");
        // System.out.println(responseEntity.getBody());
        // return responseEntity.getBody().toString();
    }
    
    
    // @GetMapping("getgeojsonlatlongresponse/{sourceCoordinates}/{destinationCoordinates}")
    @RequestMapping(value="getgeojsonlatlongresponse/{sourceCoordinates}/{destinationCoordinates}/", method=RequestMethod.GET)
    @ResponseBody
    public String getGeoJsonLatLongResponse(@PathVariable("sourceCoordinates") List<Double> sourceCooridinates,@PathVariable("destinationCoordinates") List<Double> destinationCoordinates) throws IOException {
        System.out.println(destinationCoordinates.get(1));
        return routeService.getGeoJsonLatLongResponse(sourceCooridinates,destinationCoordinates).toString();
    }
}
