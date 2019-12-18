package com.stackroute.routing.routingAlgorithms;

import com.google.gson.Gson;
import com.stackroute.routing.domain.Order;
import com.stackroute.routing.domain.Vehicle;
//import com.stackroute.routing.repository.DepotRepository;
import com.stackroute.routing.repository.OrderRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

@Service
public class Solution
{
//    private DepotRepository depotRepository;
    private OrderRepository orderRepository;
    int NoOfVehicles;
    int NoOfCustomers;
    VehicleNode[] vehicleNodes;
    double Cost;

    //Tabu Variables
    public VehicleNode[] vehiclesForBestSolution;
    double BestSolutionCost;

    public ArrayList<Double> PastSolutions;


    @Autowired
    public Solution( OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void solution(int CustNum, int VechNum, long[] VechCap)
    {

        this.NoOfVehicles = VechNum;
        this.NoOfCustomers = CustNum;
        this.Cost = 0;
        vehicleNodes = new VehicleNode[NoOfVehicles];
        vehiclesForBestSolution =  new VehicleNode[NoOfVehicles];
        PastSolutions = new ArrayList<>();

        for (int i = 0 ; i < NoOfVehicles; i++)
        {
            vehicleNodes[i] = new VehicleNode(i+1,VechCap[i]);
            vehiclesForBestSolution[i] = new VehicleNode(i+1,VechCap[i]);
        }
    }

    public boolean UnassignedCustomerExists(Node[] Nodes)
    {
        for (int i = 1; i < Nodes.length; i++)
        {
            if (!Nodes[i].IsRouted)
                return true;
        }
        return false;
    }

    public void GreedySolution(Node[] Nodes , double[][] CostMatrix) {
        System.out.println(Nodes.length);
        double CandCost,EndCost;
        int VehIndex = 0;

        while (UnassignedCustomerExists(Nodes)) {

            int CustIndex = 0;
            Node Candidate = null;
            double minCost = (float) Double.MAX_VALUE;

            if (vehicleNodes[VehIndex].Route.isEmpty())
            {
                vehicleNodes[VehIndex].AddNode(Nodes[0]);
                System.out.println("added");
            }

            for (int i = 1; i <= NoOfCustomers; i++) {
                if (Nodes[i].IsRouted == false) {
                    if (vehicleNodes[VehIndex].CheckIfFits(Nodes[i].demand)) {
                        CandCost = CostMatrix[vehicleNodes[VehIndex].CurLoc][i];
                        if (minCost > CandCost) {
                            minCost = CandCost;
                            CustIndex = i;
                            Candidate = Nodes[i];
                        }
                    }
                }
            }

            if ( Candidate  == null)
            {
                //Not a single Customer Fits
                if ( VehIndex+1 < vehicleNodes.length ) //We have more vehicles to assign
                {
                    if (vehicleNodes[VehIndex].CurLoc != 0) {//End this route
                        EndCost = CostMatrix[vehicleNodes[VehIndex].CurLoc][0];
                        vehicleNodes[VehIndex].AddNode(Nodes[0]);
                        this.Cost +=  EndCost;
                    }
                    VehIndex = VehIndex+1; //Go to next Vehicle
                }
                else //We DO NOT have any more vehicle to assign. The problem is unsolved under these parameters
                {
                    System.out.println("\nThe rest customers do not fit in any Vehicle\n" +
                            "The problem cannot be resolved under these constrains");
                    System.exit(0);
                }
            }
            else
            {
                System.out.println("customer added");
                vehicleNodes[VehIndex].AddNode(Candidate);//If a fitting Customer is Found
                Nodes[CustIndex].IsRouted = true;
                this.Cost += minCost;
            }
        }
        System.out.println("route size:"+vehicleNodes[0].Route.size());

        EndCost = CostMatrix[vehicleNodes[VehIndex].CurLoc][0];
        vehicleNodes[VehIndex].AddNode(Nodes[0]);
        this.Cost +=  EndCost;

    }


    public void TabuSearch(int TABU_Horizon, double[][] CostMatrix) {

        //We use 1-0 exchange move
        ArrayList<Node> RouteFrom;
        ArrayList<Node> RouteTo;

        long MovingNodeDemand = 0;

        int VehIndexFrom,VehIndexTo;
        double BestNCost,NeigthboorCost;

        int SwapIndexA = -1, SwapIndexB = -1, SwapRouteFrom =-1, SwapRouteTo=-1;

        int MAX_ITERATIONS = 200;
        int iteration_number= 0;

        int DimensionCustomer = CostMatrix[1].length;
        int TABU_Matrix[][] = new int[DimensionCustomer+1][DimensionCustomer+1];

        BestSolutionCost = this.Cost; //Initial Solution Cost

        boolean Termination = false;

        while (!Termination)
        {
            iteration_number++;
            BestNCost = Double.MAX_VALUE;

            for (VehIndexFrom = 0; VehIndexFrom <  this.vehicleNodes.length; VehIndexFrom++) {
                RouteFrom =  this.vehicleNodes[VehIndexFrom].Route;
                int RoutFromLength = RouteFrom.size();
                for (int i = 1; i < RoutFromLength - 1; i++) { //Not possible to move depot!

                    for (VehIndexTo = 0; VehIndexTo <  this.vehicleNodes.length; VehIndexTo++) {
                        RouteTo =   this.vehicleNodes[VehIndexTo].Route;
                        int RouteTolength = RouteTo.size();
                        for (int j = 0; (j < RouteTolength - 1); j++) {//Not possible to move after last Depot!

                            MovingNodeDemand = RouteFrom.get(i).demand;

                            if ((VehIndexFrom == VehIndexTo) ||  this.vehicleNodes[VehIndexTo].CheckIfFits(MovingNodeDemand)) {
                                //If we assign to a different route check capacity constrains
                                //if in the new route is the same no need to check for capacity

                                if (((VehIndexFrom == VehIndexTo) && ((j == i) || (j == i - 1))) == false)  // Not a move that Changes solution cost
                                {
                                    double MinusCost1 = CostMatrix[RouteFrom.get(i - 1).NodeId][RouteFrom.get(i).NodeId];
                                    double MinusCost2 = CostMatrix[RouteFrom.get(i).NodeId][RouteFrom.get(i + 1).NodeId];
                                    double MinusCost3 = CostMatrix[RouteTo.get(j).NodeId][RouteTo.get(j + 1).NodeId];

                                    double AddedCost1 = CostMatrix[RouteFrom.get(i - 1).NodeId][RouteFrom.get(i + 1).NodeId];
                                    double AddedCost2 = CostMatrix[RouteTo.get(j).NodeId][RouteFrom.get(i).NodeId];
                                    double AddedCost3 = CostMatrix[RouteFrom.get(i).NodeId][RouteTo.get(j + 1).NodeId];

                                    //Check if the move is a Tabu! - If it is Tabu break
                                    if ((TABU_Matrix[RouteFrom.get(i - 1).NodeId][RouteFrom.get(i+1).NodeId] != 0)
                                            || (TABU_Matrix[RouteTo.get(j).NodeId][RouteFrom.get(i).NodeId] != 0)
                                            || (TABU_Matrix[RouteFrom.get(i).NodeId][RouteTo.get(j+1).NodeId] != 0)) {
                                        break;
                                    }

                                    NeigthboorCost = AddedCost1 + AddedCost2 + AddedCost3
                                            - MinusCost1 - MinusCost2 - MinusCost3;

                                    if (NeigthboorCost < BestNCost) {
                                        BestNCost = NeigthboorCost;
                                        SwapIndexA = i;
                                        SwapIndexB = j;
                                        SwapRouteFrom = VehIndexFrom;
                                        SwapRouteTo = VehIndexTo;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int o = 0; o < TABU_Matrix[0].length;  o++) {
                for (int p = 0; p < TABU_Matrix[0].length ; p++) {
                    if (TABU_Matrix[o][p] > 0)
                    { TABU_Matrix[o][p]--; }
                }
            }

            RouteFrom =  this.vehicleNodes[SwapRouteFrom].Route;
            RouteTo =  this.vehicleNodes[SwapRouteTo].Route;
            this.vehicleNodes[SwapRouteFrom].Route = null;
            this.vehicleNodes[SwapRouteTo].Route = null;

            Node SwapNode = RouteFrom.get(SwapIndexA);

            int NodeIDBefore = RouteFrom.get(SwapIndexA-1).NodeId;
            int NodeIDAfter = RouteFrom.get(SwapIndexA+1).NodeId;
            int NodeID_F = RouteTo.get(SwapIndexB).NodeId;
            int NodeID_G = RouteTo.get(SwapIndexB+1).NodeId;

            Random TabuRan = new Random();
            int RendomDelay1 = TabuRan.nextInt(5);
            int RendomDelay2 = TabuRan.nextInt(5);
            int RendomDelay3 = TabuRan.nextInt(5);

            TABU_Matrix[NodeIDBefore][SwapNode.NodeId] = TABU_Horizon + RendomDelay1;
            TABU_Matrix[SwapNode.NodeId][NodeIDAfter]  = TABU_Horizon + RendomDelay2 ;
            TABU_Matrix[NodeID_F][NodeID_G] = TABU_Horizon + RendomDelay3;

            RouteFrom.remove(SwapIndexA);

            if (SwapRouteFrom == SwapRouteTo) {
                if (SwapIndexA < SwapIndexB) {
                    RouteTo.add(SwapIndexB, SwapNode);
                } else {
                    RouteTo.add(SwapIndexB + 1, SwapNode);
                }
            }
            else
            {
                RouteTo.add(SwapIndexB+1, SwapNode);
            }


            this.vehicleNodes[SwapRouteFrom].Route = RouteFrom;
            this.vehicleNodes[SwapRouteFrom].load -= MovingNodeDemand;

            this.vehicleNodes[SwapRouteTo].Route = RouteTo;
            this.vehicleNodes[SwapRouteTo].load += MovingNodeDemand;

            PastSolutions.add(this.Cost);

            this.Cost  += BestNCost;

            if (this.Cost <   BestSolutionCost)
            {
                SaveBestSolution();
            }

            if (iteration_number == MAX_ITERATIONS)
            {
                Termination = true;
            }
        }

        this.vehicleNodes = vehiclesForBestSolution;
        this.Cost = BestSolutionCost;

        try{
            PrintWriter writer = new PrintWriter("PastSolutionsTabu.txt", "UTF-8");
            writer.println("Solutions"+"\t");
            for  (int i = 0; i< PastSolutions.size(); i++){
                writer.println(PastSolutions.get(i)+"\t");
            }
            writer.close();
        } catch (Exception e) {}
    }

    public void SaveBestSolution()
    {
        BestSolutionCost = Cost;
        for (int j=0 ; j < NoOfVehicles ; j++)
        {
            vehiclesForBestSolution[j].Route.clear();
            if (! vehicleNodes[j].Route.isEmpty())
            {
                int RoutSize = vehicleNodes[j].Route.size();
                for (int k = 0; k < RoutSize ; k++) {
                    Node n = vehicleNodes[j].Route.get(k);
                    vehiclesForBestSolution[j].Route.add(n);
                }
            }
        }
    }


    public void InterRouteLocalSearch(Node[] Nodes,  double[][] CostMatrix) {

        //We use 1-0 exchange move
        ArrayList<Node> RouteFrom;
        ArrayList<Node> RouteTo;

        long MovingNodeDemand = 0;

        int VehIndexFrom,VehIndexTo;
        double BestNCost,NeigthboorCost;

        int SwapIndexA = -1, SwapIndexB = -1, SwapRouteFrom =-1, SwapRouteTo=-1;

        int MAX_ITERATIONS = 1000000;
        int iteration_number= 0;

        boolean Termination = false;

        while (!Termination)
        {
            iteration_number++;
            BestNCost = Double.MAX_VALUE;

            for (VehIndexFrom = 0; VehIndexFrom < this.vehicleNodes.length; VehIndexFrom++) {
                RouteFrom = this.vehicleNodes[VehIndexFrom].Route;
                int RoutFromLength = RouteFrom.size();
                for (int i = 1; i < RoutFromLength - 1; i++) { //Not possible to move depot!

                    for (VehIndexTo = 0; VehIndexTo < this.vehicleNodes.length; VehIndexTo++) {
                        RouteTo =  this.vehicleNodes[VehIndexTo].Route;
                        int RouteTolength = RouteTo.size();
                        for (int j = 0; (j < RouteTolength - 1); j++) {//Not possible to move after last Depot!

                            MovingNodeDemand = RouteFrom.get(i).demand;
                            if ( (VehIndexFrom == VehIndexTo) ||  this.vehicleNodes[VehIndexTo].CheckIfFits(MovingNodeDemand) )
                            {
                                if (( (VehIndexFrom == VehIndexTo) && ((j == i) || (j == i - 1)) ) == false)  // Not a move that Changes solution cost
                                {
                                    double MinusCost1 = CostMatrix[RouteFrom.get(i - 1).NodeId][RouteFrom.get(i).NodeId];
                                    double MinusCost2 = CostMatrix[RouteFrom.get(i).NodeId][RouteFrom.get(i + 1).NodeId];
                                    double MinusCost3 = CostMatrix[RouteTo.get(j).NodeId][RouteTo.get(j + 1).NodeId];

                                    double AddedCost1 = CostMatrix[RouteFrom.get(i - 1).NodeId][RouteFrom.get(i + 1).NodeId];
                                    double AddedCost2 = CostMatrix[RouteTo.get(j).NodeId][RouteFrom.get(i).NodeId];
                                    double AddedCost3 = CostMatrix[RouteFrom.get(i).NodeId][RouteTo.get(j + 1).NodeId];

                                    NeigthboorCost = AddedCost1 + AddedCost2 + AddedCost3
                                            - MinusCost1 - MinusCost2 - MinusCost3;

                                    if (NeigthboorCost < BestNCost) {
                                        BestNCost = NeigthboorCost;
                                        SwapIndexA = i;
                                        SwapIndexB = j;
                                        SwapRouteFrom = VehIndexFrom;
                                        SwapRouteTo = VehIndexTo;

                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (BestNCost < 0) {// If Best Neightboor Cost is better than the current

                RouteFrom = this.vehicleNodes[SwapRouteFrom].Route;
                RouteTo = this.vehicleNodes[SwapRouteTo].Route;
                this.vehicleNodes[SwapRouteFrom].Route = null;
                this.vehicleNodes[SwapRouteTo].Route = null;

                Node SwapNode = RouteFrom.get(SwapIndexA);

                RouteFrom.remove(SwapIndexA);

                if (SwapRouteFrom == SwapRouteTo) {
                    if (SwapIndexA < SwapIndexB) {
                        RouteTo.add(SwapIndexB, SwapNode);
                    } else {
                        RouteTo.add(SwapIndexB + 1, SwapNode);
                    }
                }
                else
                {
                    RouteTo.add(SwapIndexB+1, SwapNode);
                }

                this.vehicleNodes[SwapRouteFrom].Route = RouteFrom;
                this.vehicleNodes[SwapRouteFrom].load -= MovingNodeDemand;

                this.vehicleNodes[SwapRouteTo].Route = RouteTo;
                this.vehicleNodes[SwapRouteTo].load += MovingNodeDemand;

                PastSolutions.add(this.Cost);
                this.Cost  += BestNCost;
            }
            else{
                Termination = true;
            }

            if (iteration_number == MAX_ITERATIONS)
            {
                Termination = true;
            }
        }
        PastSolutions.add(this.Cost);

        try{
            PrintWriter writer = new PrintWriter("PastSolutionsInter.txt", "UTF-8");
            for  (int i = 0; i< PastSolutions.size(); i++){
                writer.println(PastSolutions.get(i)+"\t");
            }
            writer.close();
        } catch (Exception e) {}
    }

    public void IntraRouteLocalSearch(Node[] Nodes,  double[][] CostMatrix) {

        //We use 1-0 exchange move
        ArrayList<Node> rt;
        double BestNCost,NeigthboorCost;

        int SwapIndexA = -1, SwapIndexB = -1, SwapRoute =-1;

        int MAX_ITERATIONS = 1000000;
        int iteration_number= 0;

        boolean Termination = false;

        while (!Termination)
        {
            iteration_number++;
            BestNCost = Double.MAX_VALUE;

            for (int VehIndex = 0; VehIndex < this.vehicleNodes.length; VehIndex++) {
                rt = this.vehicleNodes[VehIndex].Route;
                int RoutLength = rt.size();

                for (int i = 1; i < RoutLength - 1; i++) { //Not possible to move depot!

                    for (int j =  0 ; (j < RoutLength-1); j++) {//Not possible to move after last Depot!

                        if ( ( j != i ) && (j != i-1) ) { // Not a move that cHanges solution cost

                            double MinusCost1 = CostMatrix[rt.get(i-1).NodeId][rt.get(i).NodeId];
                            double MinusCost2 =  CostMatrix[rt.get(i).NodeId][rt.get(i+1).NodeId];
                            double MinusCost3 =  CostMatrix[rt.get(j).NodeId][rt.get(j+1).NodeId];

                            double AddedCost1 = CostMatrix[rt.get(i-1).NodeId][rt.get(i+1).NodeId];
                            double AddedCost2 = CostMatrix[rt.get(j).NodeId][rt.get(i).NodeId];
                            double AddedCost3 = CostMatrix[rt.get(i).NodeId][rt.get(j+1).NodeId];

                            NeigthboorCost = AddedCost1 + AddedCost2 + AddedCost3
                                    - MinusCost1 - MinusCost2 - MinusCost3;

                            if (NeigthboorCost < BestNCost) {
                                BestNCost = NeigthboorCost;
                                SwapIndexA  = i;
                                SwapIndexB  = j;
                                SwapRoute = VehIndex;

                            }
                        }
                    }
                }
            }

            if (BestNCost < 0) {

                rt = this.vehicleNodes[SwapRoute].Route;

                Node SwapNode = rt.get(SwapIndexA);

                rt.remove(SwapIndexA);

                if (SwapIndexA < SwapIndexB)
                { rt.add(SwapIndexB, SwapNode); }
                else
                { rt.add(SwapIndexB+1, SwapNode); }

                PastSolutions.add(this.Cost);
                this.Cost  += BestNCost;
            }
            else{
                Termination = true;
            }

            if (iteration_number == MAX_ITERATIONS)
            {
                Termination = true;
            }
        }
        PastSolutions.add(this.Cost);

        try{
            PrintWriter writer = new PrintWriter("PastSolutionsIntra.txt", "UTF-8");
            for  (int i = 0; i< PastSolutions.size(); i++){
                writer.println(PastSolutions.get(i)+"\t");
            }
            writer.close();
        } catch (Exception e) {}
    }

    public JSONObject SolutionPrint(String Solution_Label,double[][] distanceMatrix, Vehicle[] Vehicles, JSONArray coordinates, int wholesalerId, Order[] Orders,String depotAddress)//Print Solution In console
    {
        System.out.println("=========================================================");
        System.out.println(Solution_Label+"\n");

        JSONObject Routes =new JSONObject();
        JSONObject routes =new JSONObject();
        for (int j=0 ; j < NoOfVehicles ; j++)
        {
            JSONObject  values= new JSONObject();
            JSONArray sortedOrders =new JSONArray();
            values.put("wholesalerId",wholesalerId);

            values.put("depotAddress",depotAddress);
            values.put("depotLatitude",coordinates.getJSONObject(0).getFloat("latitude"));
            values.put("depotLongitude",coordinates.getJSONObject(0).getFloat("longitude"));
            System.out.println("Route length:"+vehicleNodes[j].Route.size());
            if (! vehicleNodes[j].Route.isEmpty())
            {   System.out.print("Vehicle " + j + ":");
            int RoutSize;
            if(Solution_Label.matches(".*(Inter).*")&&j==0)
                RoutSize = vehicleNodes[j].Route.size()-1;
            else
                RoutSize=vehicleNodes[j].Route.size();
                int k;
                for ( k = 1; k < RoutSize-1 ; k++) {
                    JSONObject distance =new JSONObject();

                    distance.put("distance",distanceMatrix[vehicleNodes[j].Route.get(k-1).NodeId][vehicleNodes[j].Route.get(k).NodeId]);

                    Node node =vehicleNodes[j].Route.get(k);
                    if (k == RoutSize-2)
                    {
                        System.out.print(node.address );
                    }
                    else
                    { System.out.print(node.address+ "->"); }

                    Gson gson =new Gson();
                    String order = gson.toJson(Orders[(node.NodeId)-1]);
                    JSONObject js =new JSONObject(order);
                    sortedOrders.put(js);
                    JSONObject location = new JSONObject();
                    location.put("latitude",coordinates.getJSONObject(node.NodeId).getFloat("latitude"));
                    location.put("longitude",coordinates.getJSONObject(node.NodeId).getFloat("longitude"));
                    sortedOrders.put(location);
                    sortedOrders.put(distance);
                }

                System.out.println();
                values.put("distance",distanceMatrix[vehicleNodes[j].Route.get(k-1).NodeId][vehicleNodes[j].Route.get(0).NodeId]);
                values.put("route",sortedOrders);

                routes.put(Vehicles[j].getVehicleNumber(),values);
            }
        }
        Routes.put("routes",routes);
        Routes.put("distance",Cost);
        System.out.println("\nSolution Cost "+this.Cost+"\n");

        return Routes;
    }
}




