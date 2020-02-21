package com.server.ServerSPRC.dao;


import javax.persistence.*;

@Entity
public class BookedTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(String flightIds) {
        this.flightIds = flightIds;
    }

    private String flightIds;
    public BookedTickets() {

    }

    public BookedTickets(String flightIds) {
        this.flightIds = flightIds;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Flights: " + flightIds + "\nz";
    }
}
