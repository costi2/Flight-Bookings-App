package com.server.ServerSPRC.routeplanner;

import com.server.ServerSPRC.dao.Flights;

import java.util.*;

public class Route {
    private HashMap<Integer, Integer> visited = new HashMap<>();
    private HashMap<String, Integer> locationsIds = new HashMap<>();
    private HashMap<Integer, String> idsLocations = new HashMap<>();
    private TreeSet<String> locations = new TreeSet<>();
    private HashMap<Integer, ArrayList<Node>> graph = new HashMap<>();
    private String source = null;
    private String destination = null;
    int stopsLimit = -1;
    private ArrayList<Flights> flights;

    public ArrayList<Flights> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flights> flights) {
        this.flights = flights;
    }

    public Route(ArrayList<Flights> flights) {
        this.flights = flights;
    }

    private void dfs(Node start, ArrayList<Flights> current_path, int current_cost,
                     ArrayList<ArrayList<Flights>> paths, ArrayList<Integer> costs, int stops) {
        visited.put(start.getNode(), 1);
        if(start.getFlight() != null) {
            current_path.add(start.getFlight());
            current_cost += start.getFlight().getFlight_duration();
        }
        stops++;
        if(stops <= stopsLimit) {
            for(var neigbor : graph.get(start.getNode())) {
                if (visited.get(neigbor.getNode()) == 1 && !neigbor.getFlight().getDestination().equals(destination))
                    continue;
                if(start.getFlight().getDepartureDay() > neigbor.getFlight().getDepartureDay())
                    continue;

                if(start.getFlight().getDepartureDay() == neigbor.getFlight().getDepartureDay()) {
                    if (start.getFlight().getDeparture_hour() + start.getFlight().getFlight_duration() >
                            neigbor.getFlight().getDeparture_hour())
                        continue;
                }
                dfs(neigbor, current_path, current_cost, paths, costs, stops);
            }
        }

        stops--;
        if(start.getFlight().getDestination().equals(destination)) {
            ArrayList<Flights> newPath = new ArrayList<>();
            for (var fl : current_path) {
                newPath.add(fl.clone());
            }
            paths.add(newPath);
            costs.add(current_cost);
        }

        if(start.getFlight() != null)
            current_cost -= start.getFlight().getFlight_duration();
        if(current_path.size() > 0)
            current_path.remove(current_path.size() - 1);
    }

    public ArrayList<Flights> getRoute(String source, String destination, int stopsLimit, int departureDay) {
        this.source = source;
        this.destination = destination;
        this.stopsLimit = stopsLimit;

        ArrayList<ArrayList<Flights>> paths = new ArrayList<>();

        // get all possible nodes
        for(var flight : flights) {
            locations.add(flight.getSource());
            locations.add(flight.getDestination());
        }

        // init all possible location as not visited and map the name of location to a integer value for quick access
        int id = 0;
        for(var location : locations) {
            visited.put(id, 0);
            locationsIds.put(location, id);
            idsLocations.put(id, location);
            id++;
        }

        // create the graph
        for(var entry : idsLocations.entrySet()) {
            graph.put(entry.getKey(), new ArrayList<Node>());
        }

        // add vertexes
        for(var flight : flights) {
            int sourceId = locationsIds.get(flight.getSource());
            int destinationId = locationsIds.get(flight.getDestination());
            graph.get(sourceId).add(new Node(destinationId, flight.getFlight_duration(), flight, 0));
        }

        ArrayList<Flights> curr_parth = new ArrayList<>();
        ArrayList<Integer> costs = new ArrayList<>();
        Flights dummyFlight = new Flights(-1, "No where", source,
                0, flights.get(0).getDepartureDay(), 0, 120);
        Node start = new Node(locationsIds.get(source), 0, dummyFlight, 0);

        dfs(start, curr_parth, 0, paths, costs, 0);

        ArrayList<ArrayList<Flights>> newPaths = new ArrayList<>();
        ArrayList<Integer> newCosts = new ArrayList<>();
        for(int i = 0; i < paths.size(); i++) {
            if(paths.get(i).get(i).getDepartureDay() == departureDay) {
                newPaths.add(paths.get(i));
                newCosts.add(costs.get(i));
            }
        }

        costs = newCosts;
        paths = newPaths;
        int pathsId = -1;
        int maxCost = Integer.MAX_VALUE;
        for(int i = 0; i < costs.size(); i++) {
            if(costs.get(i) < maxCost) {
                pathsId = i;
                maxCost = costs.get(i);
            }
        }

        if(pathsId == -1)
            return null;

        return paths.get(pathsId);
    }
}
