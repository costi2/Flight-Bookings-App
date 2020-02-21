package com.server.ServerSPRC.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.ServerSPRC.routeplanner.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ClientController {
    @Autowired
    private BookedTicketsRepository bookedTicketsRepository;

    @Autowired
    private FlightBookingsRepository flightBookingsRepository;

    @Autowired
    private BoughtTicketsRepository boughtTicketsRepository;

    @Autowired
    private FlightsRepository flightsRepository;

    private ObjectMapper mapper = new ObjectMapper();
    private String buildErrorMsg(String errorMsg) {
        AbstractMap<String, String> m = new HashMap<>();
        m.put("Error message", errorMsg);
        String res = "";
        try {
            res = mapper.writeValueAsString(m);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    private boolean freePlacesLeft(String flightIds) {
        //iterate through all flights to see if we can one more free place
        for(var flight : flightIds.split(",")) {
            if(!flightsRepository.existsById(Integer.parseInt(flight)))
                continue;

            Flights currentFlight = flightsRepository.getOne(Integer.parseInt(flight));
            if(currentFlight.getAvailable_booked_seats() < 1)
                return false;
        }

        //in every flight there is on free place atleast so we update them
        for(var flight : flightIds.split(",")) {
            Flights fl = flightsRepository.getOne(Integer.parseInt(flight));
            fl.setAvailable_booked_seats(fl.getAvailable_booked_seats() - 1);
        }

        return true;
    }

    @RequestMapping(value = "/bookTicket", method = {RequestMethod.POST, RequestMethod.PUT})
    public String bookTicket(@RequestBody BookedTickets ticket) {
        System.out.println(ticket);

        BookedTickets savedTicket = bookedTicketsRepository.save(ticket);
        for(var flightId : ticket.getFlightIds().split(",")) {
            int id = Integer.parseInt(flightId);
            flightBookingsRepository.save(new FlightBookings(id, savedTicket.getId()));
        }

        System.out.println("Saved Id is: " + savedTicket.getId());
        return "{ \"reservationId\":\"" + savedTicket.getId() + "\"" + "}";
    }

    @RequestMapping(value = "/buyTicket", method = {RequestMethod.POST, RequestMethod.PUT})
    public String buyTicket(@RequestBody BoughtTickets ticket){
        BoughtTickets savedTicket = boughtTicketsRepository.save(ticket);

        ArrayList<FlightBookings> records =
                new ArrayList<>(flightBookingsRepository.findByReservationId(ticket.getReservationId()));

        ArrayList<Flights> flights = new ArrayList<>();
        Flights newFlight = null;
        for (var flightBookings : records) {
            if (flightsRepository.existsById(flightBookings.getFlightId())) {
                newFlight = flightsRepository.getOne(flightBookings.getFlightId());
                newFlight.setAvailable_seats(newFlight.getAvailable_seats() - 1);
                flightsRepository.save(newFlight);
                flights.add(newFlight);
            }
        }

        flightBookingsRepository.findAll().forEach(flightBookings -> {
            if(flightBookingsRepository.existsById(ticket.getReservationId()))
                flightBookingsRepository.deleteById(ticket.getReservationId());
        });

        bookedTicketsRepository.deleteById(ticket.getReservationId());
        // if we don t have any flight we this reservation id, we raise error
        if(flights.isEmpty())
            return buildErrorMsg("Invalid Reservation ID: " + ticket.getReservationId());

        StringBuilder js = new StringBuilder("{ \"ids\":[ ");
        System.out.println(flights);
        for(int i = 0; i < flights.size() - 1; i++) {
            js.append(flights.get(i).toJson()).append(", ");
        }
        js.append(flights.get(flights.size() - 1).toJson()).append(" ] }");

        System.out.println(js);
        System.out.println("Saved Id is: " + savedTicket.getId() + js);
        return js.toString();
    }

    @RequestMapping(value = "/getOptimalRoute", method = {RequestMethod.POST, RequestMethod.PUT})
    public String getOptimalRoute(@RequestBody OptimalRouteRequest optimalRouteRequest){

        ArrayList<Flights> allFlights =
                new ArrayList<Flights>(flightsRepository.findAll());

        Route route = new Route(allFlights);
        ArrayList<Flights> flights = route.getRoute(optimalRouteRequest.getSource(),
                optimalRouteRequest.getDestination(), optimalRouteRequest.getMaxFlights(),
                optimalRouteRequest.getDepartureDay());

        if(flights == null)
            return "{ \"response\":\"No possible route was founded\" }";

        StringBuilder js = new StringBuilder("{ \"ids\":[ ");
        System.out.println(flights);
        for(int i = 1; i < flights.size() - 1; i++) {
            js.append(flights.get(i).toJson()).append(", ");
        }
        js.append(flights.get(flights.size() - 1).toJson()).append(" ] }");

        return js.toString();
    }
}
