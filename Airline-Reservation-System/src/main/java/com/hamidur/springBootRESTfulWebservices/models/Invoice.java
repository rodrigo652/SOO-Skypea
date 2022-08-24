package com.hamidur.springBootRESTfulWebservices.models;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    @OneToOne
    private Customer customer;

    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)
    @OneToOne
    private Flight flight;

    @Column(name = "value")
    private Long value;

    @Column(name = "method")
    private String method;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
