package com.stackroute.routing.service;


import com.google.gson.Gson;
import com.stackroute.routing.domain.Order;

import com.stackroute.routing.domain.Route;
import com.stackroute.routing.domain.Vehicle;
import com.stackroute.routing.repository.OrderRepository;
//import com.stackroute.routing.repository.VehicleRepository;
import com.stackroute.routing.repository.RouteRepository;
import com.stackroute.routing.routingAlgorithms.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.routing.routingAlgorithms.Node;
import com.stackroute.routing.routingAlgorithms.VehicleNode;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


@Service
public class RouteServiceImpl implements RouteService {
    // static {
    //     System.loadLibrary("jniortools");
    // }
    @Autowired
    Solution s;
    //    private DepotRepository depotRepository;
    private OrderRepository orderRepository;
    private RouteRepository routeRepository;
    private static final Logger
            logger = Logger.getLogger(RouteServiceImpl.class.getName());;
    @Autowired
    public RouteServiceImpl(OrderRepository orderRepository,RouteRepository routeRepository) {
        this.orderRepository = orderRepository;
        this.routeRepository=routeRepository;
//        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Route> getAllRoutes() {
        Iterator<Route> it =routeRepository.findAll().iterator();
        List<Route> routes = new  ArrayList<>();
        while (it.hasNext())
        {
            routes.add(it.next());
        }
        return routes;
    }

    @Override
    public void delDuplicateRoutes(JSONObject route, int wholesalerId,String slot) {
        List<Route> routes=routeRepository.findByWholesalerIdAndSlot(wholesalerId,slot);
        Iterator<Route> it =routes.iterator();
        while (it.hasNext())
        {
            routeRepository.deleteById(it.next().getId());
        }
    }

    @Override
    public void saveRoute(JSONObject route,int wholesalerId,String slot) {
        delDuplicateRoutes(route,wholesalerId,slot);
        Iterator<String> vehicleNums = route.keys();

        Iterator<Route> itr =routeRepository.findAll().iterator();
        int num=0;
        while (itr.hasNext())
        {
            num=itr.next().getId();
        }
        num++;
        while (vehicleNums.hasNext())
        {

            Route newROute =new Route();
            newROute.setId(num);
            newROute.setWholesalerId(wholesalerId);
            String vehicleNum = vehicleNums.next();
            newROute.setVehicleNumber(vehicleNum);
            JSONObject vehicleRoute =route.getJSONObject(vehicleNum);
            newROute.setDepotAddress(vehicleRoute.getString("depotAddress"));
            newROute.setDepotLatitude(vehicleRoute.getFloat("depotLatitude"));
            newROute.setDepotLongitude(vehicleRoute.getFloat("depotLongitude"));
            newROute.setDistance(vehicleRoute.getDouble("distance"));

            newROute.setSlot(slot);
            newROute.setRoutes(vehicleRoute.getJSONArray("route").toString());
            routeRepository.save(newROute);
            System.out.println(newROute.getRoutes());
            num++;
        }
    }

    @Override
    public String getRoutesByVehicle(String vehicleNumber, String slot) {

        System.out.println("in routes:"+vehicleNumber+"  "+slot);
        List<Route> routes = routeRepository.findByVehicleNumberAndSlot(vehicleNumber,slot);
//        Iterator<Route> it = routes.iterator();
//        boolean routeAssigned=false;
//        Route route=null;
//        while (it.hasNext())
//        {
//            route =it.next();
//            if(route.getSlot()==slot)
//            {
//                routeAssigned=true;
//                break;
//            }
//        }
//        if(routeAssigned)
//        {
//            return route.getRoutes();
//        }
        if(routes!=null)
        {
            System.out.println(routes);
            Gson gson = new Gson();
            return gson.toJson(routes);

        }
        else
        {
            return  "nope! don't try it";
        }

    }

    @Override
    public String getRoutesByWholesaler(int wholesalerId, String slot) {
        List<Route> routes =routeRepository.findByWholesalerIdAndSlot(wholesalerId,slot);
        if(routes!=null)
        {
            System.out.println(routes);
            Gson gson = new Gson();
            return gson.toJson(routes);

        }
        else
        {
            return  "nope! don't try it";
        }
    }

    @Override
    public String getRoutes(String slot,JSONArray vehicleJson,String depotAddress,int wholesalerId) throws Exception {

        List<Order> orders = orderRepository.findByWholesalerIdAndSlot(wholesalerId,slot);

        String[] adressess = new String[orders.size()+1];
        adressess[0]=depotAddress;
        Iterator<Order> it =orders.iterator();
        int i=1;
        while (it.hasNext())
        {
            String address=it.next().getCustomerAddress();
            address=address.replaceAll("\\s","%20");
            address=address+"%20bangalore";
            adressess[i]=address;
            i++;
        }
        for(int j=0;j<adressess.length;j++)
        {
            System.out.println(adressess[j]);
        }
        JSONObject coordinates = coordinateFinder(adressess);
        JSONArray JS =coordinates.getJSONArray("origins");
        double distance[][] = jsonParser(coordinates);
        return routeOptimizer(slot,distance,wholesalerId,JS,vehicleJson,adressess);

    }

    @Override
    public JSONObject coordinateFinder(String[] addresses) throws Exception {
        System.out.println("insdie the coordinateFinder ");
        String key = "Am645nTS2rVqgDNr8UDKqZPdOL-X2_Z94sS5-GqjNBcoMfSOi_dVC6KTDGxL_jDb";

        JSONArray allLocations=new JSONArray();
        JSONObject obj_JSONObject;
        for(int i=0;i<addresses.length;i++)
        {


            JSONObject locationCoordinates=new JSONObject();
            String url = "http://dev.virtualearth.net/REST/v1/Locations?query="+addresses[i]+"&includeNeighborhood=1&include=1&maxResults=5&key="+key;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();


            System.out.println(response.toString());
            obj_JSONObject = new JSONObject(response.toString());
            JSONObject point=obj_JSONObject.getJSONArray("resourceSets")
                    .getJSONObject(0)
                    .getJSONArray("resources")
                    .getJSONObject(0)
                    .getJSONObject("point");


            float latitude=point.getJSONArray("coordinates").getFloat(0);
            float longitude=point.getJSONArray("coordinates").getFloat(1);
//                System.out.println(latitude+" "+longitude);
            locationCoordinates.put("latitude",latitude);
            locationCoordinates.put("longitude",longitude);
            allLocations.put(i,locationCoordinates);

        }

        //                System.out.println(allLocations.toString());
        JSONObject requestObject=new JSONObject();
        requestObject.put("origins",allLocations);
        requestObject.put("destinations",allLocations);
        requestObject.put("travelMode","driving");
        System.out.println(requestObject.toString());

        return requestObject;
    }

    @Override
    public double[][] jsonParser(JSONObject requestBody) throws Exception {
        String key = "Am645nTS2rVqgDNr8UDKqZPdOL-X2_Z94sS5-GqjNBcoMfSOi_dVC6KTDGxL_jDb";
        String url = "https://dev.virtualearth.net/REST/v1/Routes/DistanceMatrix?key="+key;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("POST");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setDoOutput(true);
        con.setDoInput(true);
//                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String inputLine;
//                    StringBuffer response = new StringBuffer();
//                    while ((inputLine = in .readLine()) != null) {
//                        response.append(inputLine);
//                    } in .close();
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(requestBody.toString());
        wr.flush();

//display what returns the POST request

        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            System.out.println("" + sb.toString());
        } else {
            System.out.println(con.getResponseMessage());
        }
        //  System.out.println(response.toString());
        JSONObject obj_JSONObject = new JSONObject(sb.toString());
        JSONArray results=obj_JSONObject.getJSONArray("resourceSets").getJSONObject(0).getJSONArray("resources").getJSONObject(0).getJSONArray("results");
        JSONObject resources=obj_JSONObject.getJSONArray("resourceSets").getJSONObject(0).getJSONArray("resources").getJSONObject(0);
        int noOfDestinations=resources.getJSONArray("destinations").length();
        int noOfOrigins=resources.getJSONArray("origins").length();
        double [][]distancematrix =new double[noOfDestinations][noOfOrigins];
        int total=noOfDestinations*noOfOrigins;
        int i=0,j=0;
        for (i=0;i<total;i++)
        {
            int rowIndex=results.getJSONObject(i).getInt("destinationIndex");
            int colIndex=results.getJSONObject(i).getInt("originIndex");
            distancematrix[rowIndex][colIndex]=results.getJSONObject(i).getFloat("travelDistance");
        }
        for (i = 0; i <noOfDestinations ; i++)
        {
            for (j = 0; j < noOfOrigins; j++)
            {
                System.out.print(distancematrix[i][j]+"\t");
            }
            System.out.println("\n");
        }
        return distancematrix;
    }

    @Override
    public String routeOptimizer(String slot,double[][] distanceMatrix,int wholesalerId,JSONArray coordinates,JSONArray vehicleJson,String[] addresses) throws Exception {


        for (int i = 0; i < coordinates.length(); i++) {
            System.out.println(coordinates.getJSONObject(i));
        }
        List<Order> orders = orderRepository.findByWholesalerIdAndSlot(wholesalerId,slot);
        Long[] orderVolumes = new Long[orders.size()+1];
        Iterator<Order> it =orders.iterator();
        Order Orders[] =new Order[orders.size()];
        orderVolumes[0]= 0L;
        int i=1,j=0;
        System.out.println("here");
        while (it.hasNext())
        {
            Orders[j] =it.next();
            orderVolumes[i]= Orders[j].getOrderVolume();
            System.out.println(orderVolumes[i]);
            i++;j++;
        }
        final Long[] demands = orderVolumes;
        System.out.println("demands : "+demands.toString());
        List<Vehicle> vehicles =new  ArrayList<>();
        for(int k=0;k<vehicleJson.length();k++)
        {
            JSONObject vehicleJsonobj = vehicleJson.getJSONObject(k);

            Gson g = new Gson();
            Vehicle vehicle = g.fromJson(vehicleJsonobj.toString(),Vehicle.class);
            vehicles.add(vehicle);
        }
        long[] capacities = new long[vehicles.size()];
        Vehicle Vehicles[] =new Vehicle[vehicles.size()];
//        List<Vehicle> vehicles=vehicleRepository.findAll();
        Iterator<Vehicle> It = vehicles.iterator();
        i=0;

        while (It.hasNext())
        {
            Vehicles[i]=It.next();
            capacities[i]=Vehicles[i].getCapacity();
            System.out.println(capacities[i]);
            i++;
        }
        final long[] vehicleCapacities = capacities;
        // [END demands_capacities]
        final int vehicleNumber = i;
        System.out.println("vech no:"+vehicleNumber);
//        final int depot = 0;
        int TABU_Horizon = 10;

        //Initialise
        //Create Random Customers
        Node[] Nodes = new Node[demands.length];
        Node depot = new Node(coordinates.getJSONObject(0).getFloat("latitude"),coordinates.getJSONObject(0).getFloat("longitude"),addresses[0]);
        System.out.println("here1");
        System.out.println(coordinates.length()+" "+capacities.length+" "+demands.length);
        Nodes[0] = depot;
        for (i = 1; i <demands.length; i++) {
            Nodes[i] = new Node(i, //Id ) is reserved for depot
                    coordinates.getJSONObject(i).getFloat("latitude"),
                    coordinates.getJSONObject(i).getFloat("longitude"),
                    demands[i] ,//Random Demand
                    addresses[i]
            );
        }
        System.out.println("here2");
        System.out.println("Attempting to resolve Vehicle Routing Problem (VRP) for "+demands.length+
                " Customers and "+vehicleNumber+" Vehicles"+" with variable units of capacity\n");


        s.solution(demands.length-1, vehicleNumber, capacities);

        JSONObject routes =new JSONObject();
        s.GreedySolution(Nodes, distanceMatrix);

        Double minDistance=Double.MAX_VALUE;
        String minRoute="";

        routes.put("greedy",s.SolutionPrint("Greedy Solution",distanceMatrix,Vehicles,coordinates,wholesalerId,Orders,addresses[0]));

        s.IntraRouteLocalSearch(Nodes, distanceMatrix);

        routes.put("intra",s.SolutionPrint("Solution after Intra-Route Heuristic Neighborhood Search",distanceMatrix,Vehicles,coordinates,wholesalerId,Orders,addresses[0]));

        s.GreedySolution(Nodes, distanceMatrix);

        s.InterRouteLocalSearch(Nodes, distanceMatrix);

        routes.put("inter",s.SolutionPrint("Solution after Inter-Route Heuristic Neighborhood Search",distanceMatrix,Vehicles,coordinates,wholesalerId,Orders,addresses[0]));

        s.GreedySolution(Nodes, distanceMatrix);

//        s.TabuSearch(TABU_Horizon, distanceMatrix);
//
//        routes.put("tabu",s.SolutionPrint("Solution After Tabu Search",Vehicles, coordinates,wholesalerId,Orders));

        Iterator<String> keys = routes.keys();
        while (keys.hasNext())
        {
            String key = keys.next();
            if(routes.getJSONObject(key).getDouble("distance")<minDistance)
            {
                minDistance=routes.getJSONObject(key).getDouble("distance");
                minRoute=key;
            }
        }


//        RoutingIndexManager manager =
//                new RoutingIndexManager(distanceMatrix.length, vehicleNumber,depot);
//        RoutingModel routing = new RoutingModel(manager);
//        final int transitCallbackIndex =
//                routing.registerTransitCallback((long fromIndex, long toIndex) -> {
//                    int fromNode = manager.indexToNode(fromIndex);
//                    int toNode = manager.indexToNode(toIndex);
//                    return (long) distanceMatrix[fromNode][toNode];
//                });
//        routing.setArcCostEvaluatorOfAllVehicles(transitCallbackIndex);
//        final int demandCallbackIndex = routing.registerUnaryTransitCallback((long fromIndex) -> {
//            int fromNode = manager.indexToNode(fromIndex);
//            return demands[fromNode];
//        });
//        routing.addDimensionWithVehicleCapacity(demandCallbackIndex, 0, // null capacity slack
//                vehicleCapacities, // vehicle maximum capacities
//                true, // start cumul to zero
//                "Capacity");
//        RoutingSearchParameters searchParameters =
//                main.defaultRoutingSearchParameters()
//                        .toBuilder()
//                        .setFirstSolutionStrategy(FirstSolutionStrategy.Value.PATH_CHEAPEST_ARC)
//                        .build();
//                Assignment solution = routing.solveWithParameters(searchParameters);
//        JSONObject routes =new JSONObject();
//        for (i = 0; i <vehicleNumber; ++i) {
//            long index = routing.start(i);
//            float routeDistance = 0;
//            long routeLoad = 0;
//            JSONObject  values= new JSONObject();
//            JSONArray sortedOrders =new JSONArray();
//            values.put("wholesalerId",wholesalerId);
//            values.put("depotAddress",depotRepository.findByWholesalerId(wholesalerId).getDepotAddress());
//            values.put("depotLatitude",coordinates.getJSONObject(manager.indexToNode(index)).getFloat("latitude"));
//            values.put("depotLongitude",coordinates.getJSONObject((int)manager.indexToNode(index)).getFloat("longitude"));
//            while (!routing.isEnd(index)) {
//                long nodeIndex = manager.indexToNode(index);
//                if(nodeIndex!=0) {
//                    routeLoad += demands[(int) nodeIndex];
//                    sortedOrders.put(Orders[(int) nodeIndex-1]);
//                    JSONObject location = new JSONObject();
//                    location.put("latitude",coordinates.getJSONObject((int)(nodeIndex-1)).getFloat("latitude"));
//                    location.put("longitude",coordinates.getJSONObject((int)(nodeIndex-1)).getFloat("longitude"));
//                    sortedOrders.put(location);
//                }
//                long previousIndex = index;
//                index = solution.value(routing.nextVar(index));
//                routeDistance += routing.getArcCostForVehicle(previousIndex, index, i);
//                System.out.println(routeDistance);
//            }
//            values.put("routeDistance",routeDistance);
//            values.put("route",sortedOrders);
//            routes.put(Vehicles[i].getVehicleNumber(),values);
//        }
        System.out.println(routes.getJSONObject(minRoute).toString());

        String Routes =routes.getJSONObject(minRoute).getJSONObject("routes").toString();
//        Gson gson =new Gson();
//        String object=gson.toJson(routes.getJSONObject(minRoute).getJSONObject("routes").getJSONArray("route").getJSONObject(0));
//        routes.getJSONObject(minRoute).getJSONObject("routes").put("route",object);
//        System.out.println("modified:"+routes.getJSONObject(minRoute).getJSONObject("routes"));
//        Routes=Routes.replaceAll("\"Order\\(","{");
        Routes=Routes.replaceAll("\\},\\{\"l",",\"l");
        Routes=Routes.replaceAll("\\},\\{\"d",",\"d");
//        Pattern pat=Pattern.compile("[a-zA-Z]+=[a-zA-Z0-9.%-]+[,]+");
//        Matcher mat =pat.matcher(Routes);
//        System.out.println(Routes);
//        i=0;
//        while (mat.find())
//        {
//            System.out.println(mat);
//
//            String subStr = Routes.substring(mat.start()+i,mat.end()+i);
//            boolean isInteger =false;
//            if(subStr.matches(".*(id|Id).*"))
//                isInteger =true;
//            System.out.println(subStr);
//            Pattern Pat =Pattern.compile("([a-zA-Z]+)=([a-zA-Z0-9.%-]+),");
//            String temp=subStr;
//            Matcher Mat = Pat.matcher(subStr);
//            while (Mat.find()){
//                String right=Mat.group(2);
//                String left=Mat.group(1);
//                System.out.println("left:"+left+"=" +"right: "+right);
//                temp=temp.replace(left,"\""+left+"\"");
//                i+=2;
//                if(!isInteger)
//                {
//                    i+=2;
//                    temp=temp.replace(right,"\""+right+"\"");
//                }
//                System.out.println(temp);
//            }
//            Routes=Routes.replaceFirst(subStr,temp);
//        }
//        Routes=Routes.replaceAll("=",":");
//        Routes=Routes.replaceAll(",\\s",",");
        System.out.println("in route sercie:"+
                Routes);
        JSONObject newRoutes =new JSONObject(Routes);

        System.out.println("last print in route service :"+Routes);
        return Routes;
    }

    @Override
    public JSONObject getCoordinateResponse(String address) throws IOException {
        address=address.replaceAll("\\s", "%20");
        address=address+"%20bangalore";
        System.out.println("insdie the coordinateFinder ");
        String key = "Am645nTS2rVqgDNr8UDKqZPdOL-X2_Z94sS5-GqjNBcoMfSOi_dVC6KTDGxL_jDb";
        JSONObject obj_JSONObject;
            String url = "http://dev.virtualearth.net/REST/v1/Locations?query="+address+"&includeNeighborhood=1&include=1&maxResults=5&key="+key;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();


            // System.out.println(response.toString());
            obj_JSONObject = new JSONObject(response.toString());
            System.out.println(obj_JSONObject);
        return obj_JSONObject;
        }

    @Override
    public JSONObject getGeoJsonLatLongResponse(List<Double> sourceCoordinates, List<Double> destinationCoordinates) throws IOException {
        System.out.println("\n inside the geoJsonFinder \n");
        JSONObject obj_JSONObject;
        System.out.println("\n"+sourceCoordinates.get(0)+" "+sourceCoordinates.get(1)+" "+destinationCoordinates.get(0)+" "+destinationCoordinates.get(1)+"\n");
        String url = "http://dev.virtualearth.net/REST/V1/Routes?wp.0="+sourceCoordinates.get(0)+","+sourceCoordinates.get(1)+"&wp.1="+destinationCoordinates.get(0)+","+ destinationCoordinates.get(1)+"&optmz=distance&routeAttributes=routePath&key=Am645nTS2rVqgDNr8UDKqZPdOL-X2_Z94sS5-GqjNBcoMfSOi_dVC6KTDGxL_jDb";
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
        } in .close();


        System.out.println(response.toString());
        obj_JSONObject = new JSONObject(response.toString());
        return obj_JSONObject;
    }
}

