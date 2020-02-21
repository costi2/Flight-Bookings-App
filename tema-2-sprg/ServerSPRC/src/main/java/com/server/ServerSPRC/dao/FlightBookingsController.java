package com.server.ServerSPRC.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightBookingsController {
    @Autowired
    FlightBookingsRepository flightBookingsRepository;

    @GetMapping("/FlightBookings")
    public List<FlightBookings> index(){
        return flightBookingsRepository.findAll();
    }
}