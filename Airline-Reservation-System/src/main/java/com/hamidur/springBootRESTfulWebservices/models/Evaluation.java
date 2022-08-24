package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "evaluations")
public class Evaluation {

    @Id
    @Column(name = "evaluation_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer evaluationId;

    @Column(name = "evaluation_score", nullable = false)
    private Integer evaluationScore;

    @Column(name = "evaluation_comments", nullable = true)
    private String evaluationComment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;


    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Integer getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Integer evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public String getEvaluationComment() {
        return evaluationComment;
    }

    public void setEvaluationComment(String evaluationComment) {
        this.evaluationComment = evaluationComment;
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

    @Override
    public int hashCode() {
        return Objects.hash(getEvaluationId(), getEvaluationComment(), getEvaluationScore(), getCustomer(), getFlight());
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                "customerId='" + customer.getCustomerId() +
                ", flight='" + flight.getFlightCode() +
                ", score='" + evaluationScore +
                ", comment='" + evaluationComment +
                '}';
    }
}
