package com.server.ServerSPRC.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightsController {
    @Autowired
    FlightsRepository flightsRepository;

    @Autowired
    FlightBookingsRepository flightsBookingsRepository;

    @Autowired
    BookedTicketsRepository bookedTicketsRepository;

    @PersistenceContext private EntityManager entityManager;
    @GetMapping("/Flights")
    public List<Flights> index(){
        return flightsRepository.findAll();
    }

    @RequestMapping(value = "/addFlight", method = {RequestMethod.POST, RequestMethod.PUT})
    public String addFlight(@RequestBody Flights flight) {
        System.out.println(flight);
        flight.setAvailable_booked_seats(flight.getAvailable_seats() + flight.getAvailable_seats() % 10);
        flightsRepository.save(flight);

        return "{ \"response\":\"Operation succeded\" }";
    }

    @RequestMapping(value = "/cancelFlight", method = {RequestMethod.POST, RequestMethod.PUT})
    public String cancelFlight(@RequestBody CancelledFlight flight) {
        //stergem zborul dupa id
        if(!flightsRepository.existsById(flight.getFlightId()))
            return "{ \"response\":\"flightId does not exist\"}";
        flightsRepository.deleteById(flight.getFlightId());

        // luam toate zborurile din FlightBookings
        List<FlightBookings> flightBookings = flightsBookingsRepository.findByFlightId(flight.getFlightId());
        ArrayList<FlightBookings> toDelete = new ArrayList<>();
        for(var flightBooking : flightBookings) {
            if(bookedTicketsRepository.existsById(flightBooking.getReservationId())) {
                bookedTicketsRepository.deleteById(flightBooking.getReservationId());
                List<FlightBookings> flightBookingsList =
                        flightsBookingsRepository.findByReservationId(flightBooking.getReservationId());

                if(flightBookingsList != null && flightBookings.size() > 0)
                    toDelete.addAll(flightBookingsList);
            }
        }

        toDelete.forEach(flightBooking -> {
               if (flightsBookingsRepository.existsById(flightBooking.getId()))
                   flightsBookingsRepository.deleteById(flightBooking.getId());
        });

        return "{ \"response\":\"Operation succeded\" }";
    }
}
