package com.stackroute.routing.service;

import com.stackroute.routing.domain.Route;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.List;

public interface RouteService {
    public List<Route> getAllRoutes();
    public  void delDuplicateRoutes(JSONObject route,int wholesalerId,String slot);
    public String getRoutesByWholesaler(int wholesalerId,String slot);
    public void saveRoute(JSONObject route,int wholesalerId,String slot);
    public String getRoutesByVehicle(String vehicleNumber,String slot);
    public String getRoutes(String slot,JSONArray vehicleJson,String depotAddress,int wholersalerId) throws  Exception;
    public JSONObject coordinateFinder(String[] addresses) throws Exception;
    public double[][] jsonParser(JSONObject requestBody) throws Exception;
    public  String routeOptimizer(String slot,double[][] distance, int wholesalerId, JSONArray coordinates,JSONArray vehicleJson,String[] addresses) throws Exception;
    public JSONObject getCoordinateResponse(String address) throws IOException;
    public JSONObject getGeoJsonLatLongResponse(List<Double> sourceCoordinates,List<Double> destinationCoordinates) throws IOException;
}
