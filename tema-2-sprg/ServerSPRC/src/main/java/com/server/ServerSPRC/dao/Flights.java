package com.server.ServerSPRC.dao;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Flights {
    @Id
    @NotNull
    private int id;

    private String source;
    private String destination;
    private int departure_hour;
    private int departureDay;
    private int flight_duration;
    private int available_seats;
    private int available_booked_seats;

    public boolean checkForNull() {
        boolean flag = true;
        flag &= (source != null);
        flag &= (destination != null);

        return flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getDeparture_hour() {
        return departure_hour;
    }

    public void setDeparture_hour(int departure_hour) {
        this.departure_hour = departure_hour;
    }

    public int getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(int departureDay) {
        this.departureDay = departureDay;
    }

    public int getFlight_duration() {
        return flight_duration;
    }

    public void setFlight_duration(int flight_duration) {
        this.flight_duration = flight_duration;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int getAvailable_booked_seats() {
        return available_booked_seats;
    }

    public int getCost() {
        return flight_duration;
    }

    public void setAvailable_booked_seats(int available_booked_seats) {
        this.available_booked_seats = available_booked_seats;
    }

    public String toJson() {
        return "{ \"source\":\"" + source + "\", " + "\"destination\":\"" + destination + "\", "  + "\"flightDuration\":\"" + flight_duration + "\", "
                + "\"id\":\"" + this.id +"\" }";

    }
    public Flights() {  }

    public Flights(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public Flights(int id, String source, String destination, int departure_hour,
                   int departureDay, int flight_duration, int available_seats) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.departure_hour = departure_hour;
        this.departureDay = departureDay;
        this.flight_duration = flight_duration;
        this.available_seats = available_seats;
        this.available_booked_seats = this.available_seats + this.available_seats % 10;
    }

    public Flights clone() {
        Flights newFlight = new Flights();
        newFlight.setId(this.id);
        newFlight.setSource(this.getSource());
        newFlight.setDestination(this.getDestination());
        newFlight.setDeparture_hour(this.getDeparture_hour());
        newFlight.setDepartureDay(this.getDepartureDay());
        newFlight.setAvailable_seats(this.getAvailable_seats());
        newFlight.setAvailable_booked_seats(this.getAvailable_booked_seats());
        newFlight.setFlight_duration(this.getFlight_duration());
        return newFlight;
    }

    public String toString() {
        return id + " " + source + " " + destination + " " + departure_hour + " " + departureDay + " " +
                flight_duration + " " + available_seats + " " + available_seats + "\n";
    }

}
