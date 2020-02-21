package com.server.ServerSPRC.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightBookingsRepository extends JpaRepository<FlightBookings, Integer> {
    List<FlightBookings> findByFlightId(int flightId);
    List<FlightBookings> findByReservationId(int reservationId);
}
