package com.server.ServerSPRC.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookedTicketsController {
    @Autowired
    BookedTicketsRepository bookedTicketsRepository;

    @Autowired
    FlightBookingsRepository flightBookingsRepository;

    @GetMapping("/BookedTickets")
    public List<BookedTickets> index(){
        return bookedTicketsRepository.findAll();
    }
}