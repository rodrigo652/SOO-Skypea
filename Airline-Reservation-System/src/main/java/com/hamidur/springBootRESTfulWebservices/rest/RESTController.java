package com.hamidur.springBootRESTfulWebservices.rest;

import com.hamidur.springBootRESTfulWebservices.dto.DadosLogin;
import com.hamidur.springBootRESTfulWebservices.dto.UserAutheticatedDTO;
import com.hamidur.springBootRESTfulWebservices.dto.UserRegistrationDTO;
import com.hamidur.springBootRESTfulWebservices.errors.InvalidRequestException;
import com.hamidur.springBootRESTfulWebservices.errors.InvalidRequestExceptionResponse;
import com.hamidur.springBootRESTfulWebservices.models.*;
import com.hamidur.springBootRESTfulWebservices.services.*;
import com.hamidur.springBootRESTfulWebservices.utils.Util;

import com.hamidur.springBootRESTfulWebservices.utils.recomender.BeanToCSV;
import com.hamidur.springBootRESTfulWebservices.utils.recomender.RecommenderBuilder;
import com.hamidur.springBootRESTfulWebservices.utils.recomender.RecommenderReviews;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/public")
public class RESTController
{
    private final AirportService airportService;
    private final AirlineService airlineService;
    private final AirplaneService airplaneService;
    private final CustomerService customerService;
    private final FlightService flightService;
    private final ReservationService reservationService;
    private final EvaluationService evaluationService;
    private final UserAuthenticationService userAuthenticationService;
    private final UserRegistrationService userRegistrationService;
    private final InvoiceService invoiceService;

    @Autowired
    public RESTController(final AirportService airportService, final AirlineService airlineService,
                          final AirplaneService airplaneService, final CustomerService customerService,
                          final FlightService flightService, final ReservationService reservationService,
                          final EvaluationService evaluationService, final  UserAuthenticationService userAuthenticationService,
                          final UserRegistrationService userRegistrationService, final InvoiceService invoiceService)
    {
        this.airportService = airportService;
        this.airlineService = airlineService;
        this.airplaneService = airplaneService;
        this.customerService = customerService;
        this.flightService = flightService;
        this.reservationService = reservationService;
        this.evaluationService = evaluationService;
        this.userAuthenticationService = userAuthenticationService;
        this.userRegistrationService = userRegistrationService;
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAirports()
    {
        List<Airport> airports = airportService.getAirports();
        return airports != null ? new ResponseEntity<>(airports, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airport/{airportName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> getAirportByName(@PathVariable String airportName)
    {
        try
        {
            Airport airport = null;
            if(Util.validateAirportName(airportName))
            {
                airport = airportService.getAirportByName(airportName);
            }
            return airport != null ? new ResponseEntity<>(airport, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airport/city/{airportCity}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airport>> getAirportByCity(@PathVariable String airportCity)
    {
        try
        {
            List<Airport> airports = null;
            airports = airportService.getAirportByCity(airportCity);
            return airports != null ? new ResponseEntity<>(airports, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAirlines()
    {
        List<Airline> airlines = airlineService.getAirlines();
        return airlines != null ? new ResponseEntity<>(airlines, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/airline/{airlineName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airline> getAirlineByName(@PathVariable String airlineName)
    {
        try
        {
            Airline airline = null;
            if(Util.validateAirlineName(airlineName))
            {
                airline = airlineService.getAirlineByName(airlineName);
            }
            return airline != null ? new ResponseEntity<>(airline, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airline/{airlineName}/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Airplane>> getAirplanesByAirlineName(@PathVariable String airlineName)
    {
        try
        {
            Set<Airplane> airplanes = null;
            if(Util.validateAirlineName(airlineName)) airplanes = airlineService.getAirplanesByAirlineName(airlineName);
            return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/airplanes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airplane>> getAirplanes()
    {
        List<Airplane> airplanes = airplaneService.getAirplanes();
        return airplanes != null ? new ResponseEntity<>(airplanes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Customer>> getCustomers()
    {
        Set<Customer> customers = customerService.getCustomers();
        return customers != null ? new ResponseEntity<>(customers, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/customer/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email)
    {
        Customer customer = customerService.getCustomerByEmail(email);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/flights/{mode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Flight>> getFlights(@PathVariable Integer mode)
    {
        List<Flight> flights = Util.order(flightService.getFlights(), mode);
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/flight/{flightId}")
    public ResponseEntity<Flight> get(@PathVariable Integer flightId)
    {
        try
        {
            Flight flight = null;
            if(Util.validateNumber(flightId)) flight = flightService.getFlightById(flightId);
            return flight != null ? new ResponseEntity<>(flight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value = "/flight/{sourceAirport}/{destinyAirport}/{date}/{mode}")
    public ResponseEntity<List<Flight>> get(@PathVariable String sourceAirport, @PathVariable String destinyAirport, @PathVariable String date, @PathVariable Integer mode)
    {
        try
        {
            List<Flight> flight = null;
            flight = flightService.getFlightDestSource(sourceAirport, destinyAirport, Util.stringDateToDateTime(date));
            return flight != null ? new ResponseEntity<>(flight, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping(value = "/flights/today/{mode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Flight>> getFlightsByToday(@PathVariable Integer mode)
    {
        List<Flight> flights = Util.order(flightService.getFlightsForToday(), mode);
        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/{date}/{mode}")
    public ResponseEntity<List<Flight>> getFlightsByDate(@PathVariable String date, @PathVariable Integer mode)
    {
        try
        {
            List<Flight> flights = Util.order(flightService.getFlightsByDate(Util.stringDateToDateTime(date)), mode);
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/flights/fare/{fare}/{mode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Flight>> getFlightsByFare(@PathVariable Float fare, @PathVariable Integer mode)
    {
        List<Flight> flights = Util.order(flightService.getFlightsByFare(fare), mode);
        return flights != null && !flights.isEmpty() ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/flights/status/{status}/{mode}")
    public ResponseEntity<List<Flight>> getFlightsByStatus(@PathVariable String status, @PathVariable Integer mode)
    {
        try
        {
            List<Flight> flights = Util.order(flightService.getFlightsByStats(status), mode);
            return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/customer/{customerId}")
    public ResponseEntity<Set<Reservation>> getAllRSVPsByCustomerId(@PathVariable Integer customerId)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllRSVPsByCustomerId(customerId);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPs()
    {
        Set<Reservation> reservations = reservationService.getAllCancelledRSVPs();
        return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/rsvps/{airline}/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllActiveRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllActiveRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/rsvps/{airline}/cancelled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getAllCancelledRSVPsByAirline(@PathVariable String airline)
    {
        try
        {
            Set<Reservation> reservations = reservationService.getAllCancelledRSVPsByAirline(airline);
            return reservations != null ? new ResponseEntity<>(reservations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/flight", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flight> insertFlight(@RequestBody Flight flight)
    {
        try
        {
            return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.OK);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        catch (NullPointerException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/rsvp/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addRSVPByCustomerId(@RequestBody Map<String, Object> json)
    {
        try
        {
            return reservationService.addRSVPByCustomerId(json) ? new ResponseEntity<>(true, HttpStatus.OK) :
                    new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (IllegalArgumentException ex)
        {
            InvalidRequestException response = new InvalidRequestException(HttpStatus.CHECKPOINT.value(), ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CHECKPOINT);
        }
        catch (InvalidRequestException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(ex);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (NoSuchElementException ex)
        {
            InvalidRequestExceptionResponse response = new InvalidRequestExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/airport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport)
    {
        Airport addedAirport = airportService.addAirport(airport);
        return addedAirport != null ? new ResponseEntity<>(airport, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/rsvp/cancel/{rsvpId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelRSVPByCustomerId(@PathVariable Integer rsvpId)
    {
        try
        {
            boolean isCancelled = reservationService.cancelRSVPByCustomerId(rsvpId);
            return isCancelled ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @PutMapping(value = "/flight/cancel/{flightId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> cancelFlightById(@PathVariable Integer flightId)
    {
        return flightService.cancelFlight(flightId) ? new ResponseEntity<>(true, HttpStatus.OK) : new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping(value = "/delete/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteCustomerById(@PathVariable Integer customerId)
    {
        try
        {
            return customerService.deleteCustomerById(customerId) ? new ResponseEntity<>(true, HttpStatus.OK) :
                    new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage(), ex);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserAutheticatedDTO> autenticar(@RequestBody DadosLogin dadosLogin, @RequestHeader String Authorization){
        Customer user = userAuthenticationService.authenticate(dadosLogin, Authorization);
        return new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.ACCEPTED);
    }

    @PostMapping("/user")
    public ResponseEntity<UserAutheticatedDTO> registrate(@RequestBody UserRegistrationDTO userRegistrationDTO){
        Customer user = userRegistrationService.registrate(userRegistrationDTO.toUser());
        return  new ResponseEntity<UserAutheticatedDTO>(UserAutheticatedDTO.toDTO(user, "Bearer "), HttpStatus.CREATED);
    }

    @GetMapping(value = "/invoice/{customerId}")
    public ResponseEntity<List<Invoice>> getInvoiceByCustomerId(@PathVariable Integer customerId) {
        List<Invoice> invoice = invoiceService.findByCustomerId(customerId);
        return invoice != null ? new ResponseEntity<>(invoice, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/invoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice)
    {
        Invoice addedInvoice = invoiceService.addInvoice(invoice);
        return addedInvoice != null ? new ResponseEntity<>(addedInvoice, HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /*******************************************************************************
     *                                                                              *
     *                         Avaliação e Recomendação                             *
     *                                                                              *
     * ******************************************************************************/

    @GetMapping(value = "/evaluations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Evaluation>> getEvaluations() {
        Set<Evaluation> evaluations = evaluationService.getEvaluations();
        return evaluations != null ? new ResponseEntity<>(evaluations, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/evaluations/{customerId}")
    public ResponseEntity<Set<Evaluation>> getAllEvaluationsByCustomerId(@PathVariable Integer customerId)
    {
        try
        {
            Set<Evaluation> evaluations = evaluationService.getAllEvaluationsByCustomerId(customerId);
            return evaluations != null ? new ResponseEntity<>(evaluations, HttpStatus.OK) :
                    new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (IllegalArgumentException ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    @GetMapping(value = "/evaluations/recommend/{customerId}")
    public ResponseEntity<List<Flight>> getRecommendations (@PathVariable Integer customerId) throws IOException, TasteException {
        Set<Evaluation> evaluations = evaluationService.getEvaluations();

        List<RecommenderReviews> recommenderReviews = new ArrayList<>();

        for(Evaluation evaluation : evaluations){
            recommenderReviews.add(
                    new RecommenderReviews(evaluation.getCustomer().getCustomerId(),
                            evaluation.getFlight().getFlightId(),
                            evaluation.getEvaluationScore()));
        }

        BeanToCSV.generateCSV("Review.csv", recommenderReviews);
        File file = new File("Review.csv");
        Recommender recommender = new RecommenderBuilder().buildRecommender(new FileDataModel(file));
        List<RecommendedItem> recommendations = recommender.recommend(customerId, 3);

        List<Flight> flights = new ArrayList<Flight>();
        for(RecommendedItem item : recommendations){
            flights.add(flightService.getFlightById((int) item.getItemID()));
        }

        return flights != null ? new ResponseEntity<>(flights, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
