package com.server.ServerSPRC.routeplanner;

import com.server.ServerSPRC.dao.Flights;

import java.util.Comparator;

// Class to represent a node in the graph
class Node implements Comparator<Node> {
    private int node;
    private int cost;
    private Flights flight;
    private int stops = 0;

    public Flights getFlight() {
        return flight;
    }

    public void setFlight(Flights flight) {
        this.flight = flight;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    public Node()
    {
    }

    public Node(int node, int cost, Flights flight, int stops)
    {
        this.node = node;
        this.cost = cost;
        this.flight = flight;
        this.stops = stops;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}

