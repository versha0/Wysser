package com.stackroute.routing.routingAlgorithms;

public class Node
{
    public int NodeId;
    String address;
    public float Node_X ,Node_Y; //Node Coordinates
    public long demand; //Node Demand if Customer
    public boolean IsRouted;
    private boolean IsDepot; //True if it Depot Node

    public Node(float depot_x,float depot_y,String address) //Cunstructor for depot
    {
        this.NodeId = 0;
        this.Node_X = depot_x;
        this.Node_Y = depot_y;
        this.IsDepot = true;
        this.address=address;

    }

    public Node(int id ,float x, float y, long demand,String address) //Cunstructor for Customers
    {
        this.NodeId = id;
        this.Node_X = x;
        this.Node_Y = y;
        this.demand = demand;
        this.IsRouted = false;
        this.IsDepot = false;
        this.address=address;
    }
}
