package com.server.ServerSPRC.dao;

public class CancelledFlight {
    public CancelledFlight(int flightId) {
        this.flightId = flightId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public CancelledFlight() {
    }
    private int flightId;
}
