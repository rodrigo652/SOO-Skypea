package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight
{
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    @Column(name = "flight_code", nullable = false)
    private String flightCode;


    @Column(name = "date_time_destiny")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime arrivalDateTime;

    @OneToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "airport_id")
    private Airport arrivalAirport;

    @Column(name = "date_time_source")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime departureDateTime;

    @JoinColumn(name = "departure_airport_id", referencedColumnName = "airport_id")
    @OneToOne
    private Airport departureAirport;

    @OneToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id")
    private Airplane airplane;



    @Transient
    private Integer availableSeat;
    @Column(name = "fare")
    private Float fare;
    @Column(name = "capacity", updatable = false, nullable = false)
    private Integer capacity;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "customers_flights",
            inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")},
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "flight_id")}
    )
    private Set<Customer> customers;

    public Flight() {}

    public Integer getFlightId() {
        return flightId;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getFlightId(), flight.getFlightId()) &&
                Objects.equals(getFlightCode(), flight.getFlightCode()) &&
                Objects.equals(getAirplane(), flight.getAirplane());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getFlightCode(), getAirplane().hashCode());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightCode='" + flightCode + '\'' +
                ", airplane=" + airplane +
                ", availableSeat=" + availableSeat +
                ", fare=" + fare +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }
}
