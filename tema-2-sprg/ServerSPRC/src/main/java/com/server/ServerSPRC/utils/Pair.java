package com.server.ServerSPRC.utils;

public class Pair {
    public String getSource() {
        return source;
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

    private String source;
    private String destination;

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

    public Pair() {
        this.source = null;
        this.destination = null;
        this.departureDay = 0;
        this.maxFlights = 0;
    }

    public Pair(String source, String destination, int maxFlights, int departureDay) {
        this.source = source;
        this.destination = destination;
        this.maxFlights = maxFlights;
        this.departureDay = departureDay;
    }
}
