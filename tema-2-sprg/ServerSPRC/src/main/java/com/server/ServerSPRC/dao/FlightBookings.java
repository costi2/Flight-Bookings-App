package com.server.ServerSPRC.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightBookings {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    private int flightId;
    private int reservationId;
    public FlightBookings() {
    }

    public FlightBookings(int flightId, int reservationId) {
        this.flightId = flightId;
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "FlightID: " + this.flightId + ", " + "Reservation: " + this.reservationId + "\n";
    }
}
