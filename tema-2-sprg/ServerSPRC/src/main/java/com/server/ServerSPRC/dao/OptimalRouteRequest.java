package com.server.ServerSPRC.dao;

public class OptimalRouteRequest {
    private String source;
    private String destination;

    public String getSource() {
        return source;
    }

    public OptimalRouteRequest(String source, String destination, int maxFlights, int departureDay) {
        this.source = source;
        this.destination = destination;
        this.maxFlights = maxFlights;
        this.departureDay = departureDay;
    }

    public OptimalRouteRequest() {
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getMaxFlights() {
        return maxFlights;
    }

    public void setMaxFlights(int maxFlights) {
        this.maxFlights = maxFlights;
    }

    public int getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(int departureDay) {
        this.departureDay = departureDay;
    }

    private int maxFlights;
    private int departureDay;
}
