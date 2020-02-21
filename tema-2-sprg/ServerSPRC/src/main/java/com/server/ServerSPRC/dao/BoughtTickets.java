package com.server.ServerSPRC.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BoughtTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int reservationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreditInformation() {
        return creditInformation;
    }

    public void setCreditInformation(String creditInformation) {
        this.creditInformation = creditInformation;
    }

    private String creditInformation;

    public BoughtTickets() {

    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public BoughtTickets(String creditInformation) {
        this.creditInformation = creditInformation;
    }
}
