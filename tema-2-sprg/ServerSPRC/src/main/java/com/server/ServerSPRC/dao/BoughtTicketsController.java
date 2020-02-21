package com.server.ServerSPRC.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoughtTicketsController {
    @Autowired
    BoughtTicketsRepository boughtTicketsRepository;

    @GetMapping("/BoughtTickets")
    public List<BoughtTickets> index(){
        return boughtTicketsRepository.findAll();
    }
}
