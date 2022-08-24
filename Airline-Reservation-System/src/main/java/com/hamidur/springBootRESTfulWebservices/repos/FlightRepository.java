package com.hamidur.springBootRESTfulWebservices.repos;

import com.hamidur.springBootRESTfulWebservices.models.Flight;
import com.hamidur.springBootRESTfulWebservices.models.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer>
{
    // native query - MySQL
    @Query(value = "select * from flights f where f.date_time_source >= :date_time1 and f.date_time_source < :date_time2", nativeQuery = true)
    Set<Flight> findByCurrentDateTime(@Param("date_time1") LocalDateTime dateTime1,
                                      @Param("date_time2") LocalDateTime dateTime2);

    // native query - MySQL
    @Query(value = "select * from flights f where f.date_time_source >= :date_time", nativeQuery = true)
    Set<Flight> findByDate(@Param("date_time") LocalDateTime dateTime);

    // Hibernate query
    @Query(value = "select f from Flight f where f.fare <= :fare and f.fare >= 0")
    Set<Flight> findByFare(@Param("fare") Float fare);

    // Query derived from method name
    Set<Flight> findFlightsByStatus(Status status);

    @Modifying
    @Transactional
    @Query(value = "delete from customers_flights cf where cf.flight_id = :flight_id ; " +
                    "update flights f set f.status = :_status where f.flight_id = :flight_id ; " +
                    "update reservations r set r.status = :_status where r.status = 'ACTIVE' and r.flight_id = :flight_id ; ",
            nativeQuery = true)
    void deleteCustomersRSVPsByFlightId(@Param("flight_id") Integer flightId, @Param("_status") String flightStatus);

    @Modifying
    @Transactional
    @Query(value = "update flights f set f.status = :_status where f.flight_id = :flight_id", nativeQuery = true)
    void changeFlightStatus(@Param("_status") String status, @Param("flight_id")Integer flightId);

    @Transactional
    @Query(value = "select * from flights f inner join airports a on f.date_time_source >= :date and f.arrival_airport_id = a.airport_id and a.airport_city = :a_name inner join airports d on f.departure_airport_id = d.airport_id and d.airport_city = :d_name", nativeQuery = true)
    List<Flight> findAllByDestSource(@Param("d_name") String d_name, @Param("a_name") String a_name, @Param("date") LocalDateTime date);

    @Transactional
    @Query(value = "select * from flights where flights.arrival_airport_id = :arrival_id and flights.departure_airport_id = :departure_id ;",
    nativeQuery = true)
    List<Flight> findFlightsDestSource(@Param("arrival_id") Integer arrivalId, @Param("departure_id") Integer departureId);
}
