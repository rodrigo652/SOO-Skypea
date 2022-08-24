package com.hamidur.springBootRESTfulWebservices.utils;

import com.hamidur.springBootRESTfulWebservices.errors.InvalidRequestException;
import com.hamidur.springBootRESTfulWebservices.models.*;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util
{
    public static final String JSON_PATTERN = "MM-dd-yyyy HH:mm:ss";
    public static final String DB_TO_VIEW = "MM-dd-yyyy HH:mm:ss";
    public static final String DATE_FORMAT = "MM-dd-yyyy";

    //custom keys for json
    public static final String RSVP_ID_JKEY = "reservation_id";
    public static final String FLIGHT_ID_JKEY = "flightId";
    public static final String CUSTOMER_ID_JKEY = "customerId";
    public static final String DEPARTURE_DATE_TIME_JKEY = "departureDateTime";
    public static final String ARRIVAL_DATE_TIME_JKEY = "arrivalDateTime";

    public static boolean verifyRSVPByCustomerId(final Map<String, Object> json) throws InvalidRequestException
    {
        if(json.size() != 2) throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request must have 2 values, found="+json.size());

        if(json.getOrDefault(CUSTOMER_ID_JKEY, -1).equals(-1))
        {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request body missing key="+CUSTOMER_ID_JKEY);
        }
        if(json.getOrDefault(FLIGHT_ID_JKEY, -1).equals(-1))
        {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request body missing key="+FLIGHT_ID_JKEY);
        }
        return true;
    }

    public static LocalDateTime toViewDateTime(LocalDateTime dbDateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_TO_VIEW, Locale.US);
        String formatted = formatter.format(dbDateTime);
        return LocalDateTime.parse(formatted, formatter);
    }

    public static LocalDateTime toDBDateTime(LocalDateTime dateTime)
    {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
    }

    public static boolean validateAirlineName(String airlineName) throws IllegalArgumentException
    {
        if(airlineName == null || airlineName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for airline name.");
        return true;
    }

    public static boolean validateAirportName(String airportName) throws IllegalArgumentException
    {
        if(airportName == null || airportName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for airport name.");
        return true;
    }

    public static boolean validateEmail(String airportName) throws IllegalArgumentException
    {
        if(airportName == null || airportName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for email.");
        return true;
    }

    public static boolean validateNumber(Integer number) throws IllegalArgumentException
    {
        if(number >= 0) return true;
        else throw new IllegalArgumentException("Invalid number provided.");
    }

    public static boolean validateNumber(Float number) throws IllegalArgumentException
    {
        if(number > 0.0) return true;
        else throw new IllegalArgumentException("Invalid number provided.");
    }

    public static LocalDateTime stringDateToDateTime(String dateTime) throws IllegalArgumentException
    {
        try
        {
            if(dateTime.trim().isEmpty() || dateTime.trim().length() < 10)
                throw new IllegalArgumentException("Invalid date. format="+DATE_FORMAT);

            String[] parts = dateTime.split(" ");
            if(parts.length < 1) throw new IllegalArgumentException("Invalid date format. format="+DATE_FORMAT);
            if(parts.length == 1)
            {
                String[] dateParts = parts[0].split("-");
                if(dateParts.length < 3) throw new IllegalArgumentException("Invalid date time format. format="+JSON_PATTERN);
                return LocalDateTime.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]), 0, 0, 0);
            }
            else if(parts.length == 2)
            {
                String[] dateParts = parts[0].trim().split("-");
                String[] timeParts = parts[1].trim().split(":");

                if(dateParts.length < 3 || timeParts.length < 3) throw new IllegalArgumentException("Invalid date time format. format="+JSON_PATTERN);

                return LocalDateTime.of(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]), Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]), Integer.parseInt(timeParts[2]));
            }
            else throw new IllegalArgumentException("Invalid date/datetime given. format="+JSON_PATTERN);
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public static Status validateFlightStatus(String status) throws IllegalArgumentException
    {
        if(status.trim().equalsIgnoreCase(Status.ON_TIME.toString()))
            return Status.ON_TIME;
        else if(status.trim().equalsIgnoreCase(Status.CANCELLED.toString()))
            return Status.CANCELLED;
        else throw new IllegalArgumentException("Invalid status provided. status=ON_TIME or CANCELLED");
    }

    public static boolean validateAirport(Airport airport) throws IllegalArgumentException
    {
        if(airport == null) throw new NullPointerException("NULL argument given.");
        if(airport.getAirportId() == null || airport.getAirportId() <= 0) airport.setAirportId(null);
        if(airport.getAirportName() == null)
            throw new IllegalArgumentException("Airport name cannot be NULL.");
        if(airport.getAirportName().trim().toLowerCase().length() < 2)
            throw new IllegalArgumentException("Airport name is too short, must be at least 2");
        return true;
    }

    public static boolean validateFlight(Flight flight) throws IllegalArgumentException
    {
        if(flight == null) throw new NullPointerException("NULL flight.");
        if(flight.getFlightId() == null || flight.getFlightId() <= 0) flight.setFlightId(null);
        if(flight.getFlightCode() == null || flight.getFlightCode().trim().isEmpty() || flight.getFlightCode().trim().length() < 1)
            throw new IllegalArgumentException("Invalid flight code provided.");
        if(validateAirplane(flight.getAirplane()))
        {
            if(!isArrivalAfterDeparture(flight.getDepartureDateTime(), flight.getArrivalDateTime()))
                throw new IllegalArgumentException("Arrival time is same or before departure time.");
            if(flight.getFare() == null || flight.getFare() <= 0) throw new IllegalArgumentException("Invalid fare.");
            if(flight.getCapacity() == null || flight.getCapacity() <= 0) throw new IllegalArgumentException("Invalid capacity.");
            return true;
        }
        return false;
    }

    public static boolean validateAirplane(Airplane airplane) throws IllegalArgumentException
    {
        if(airplane == null) throw new NullPointerException("NULL argument given.");
        if(airplane.getAirplaneId() == null || airplane.getAirplaneId() <= 0) airplane.setAirplaneId(null);
        if(airplane.getAirplaneName() == null)
            throw new IllegalArgumentException("Airplane name cannot be NULL.");
        if(airplane.getAirplaneName().trim().toLowerCase().length() < 2)
            throw new IllegalArgumentException("Airplane name is too short, must be at least 2");
        return validateAirline(airplane.getAirline());
    }

    public static boolean validateAirline(Airline airline) throws IllegalArgumentException
    {
        if(airline == null) throw new NullPointerException("NULL argument given.");
        if(airline.getAirlineId() == null || airline.getAirlineId() <= 0) airline.setAirlineId(null);
        if(airline.getAirlineName() == null)
            throw new IllegalArgumentException("Airline name cannot be NULL.");
        if(airline.getAirlineName().trim().toLowerCase().length() < 2)
            throw new IllegalArgumentException("Airline name is too short, must be at least 2");
        return true;
    }


    public static boolean isArrivalAfterDeparture(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime)
    {
        return arrivalDateTime.isAfter(departureDateTime);
    }

    public static boolean validateEvaluation(Evaluation evaluation) throws IllegalArgumentException{
        if(evaluation == null) throw new NullPointerException("NULL evaluation");
        if(evaluation.getEvaluationId() == null || evaluation.getEvaluationId() <= 0) evaluation.setEvaluationId(null);
        if(evaluation.getEvaluationScore() == null)
            throw new IllegalArgumentException("Score not informed.");
        return true;
    }

    /*******************************************************************************
    *                                                                              *
    *                         Avaliação e Recomendação                             *
    *                                                                              *
    * ******************************************************************************/

    public static List<Flight> order(Set<Flight> flights, Integer mode){
        List<Flight> flightList = new ArrayList<Flight>(flights);

        if (mode == 1) {
            flightList.sort(Comparator.comparing(Flight::getFare));
            return flightList;
        }else if (mode == 2){
            flightList.sort(Comparator.comparing(flight -> {return Duration.between(flight.getDepartureDateTime(),
                    flight.getArrivalDateTime()).toMinutes();}));
            return flightList;
        }else if (mode == 3){
            Flight maxFareFlight = Collections.max(flightList, Comparator.comparing(Flight::getFare));
            Float maxFare = maxFareFlight.getFare();

            Flight maxDurationFlight = Collections.max(flightList, Comparator.comparing(flight -> {return Duration.between(flight.getDepartureDateTime(),
                    flight.getArrivalDateTime()).toMinutes();}));
            long maxDuration = Duration.between(maxDurationFlight.getDepartureDateTime(),
                    maxDurationFlight.getArrivalDateTime()).toMinutes();


            flightList.sort(Comparator.comparing(flight -> {return (Duration.between(flight.getDepartureDateTime(),
                    flight.getArrivalDateTime()).toMinutes() / maxDuration)*2 + (flight.getFare() / maxFare)*1.3 ;}));
            return flightList;
        }else
            return flightList;
    }

    public static Long flightDuration (Flight flight){
        return Duration.between(flight.getDepartureDateTime(),
                                flight.getArrivalDateTime()).toHours();
    }
}
