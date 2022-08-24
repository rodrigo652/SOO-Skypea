package com.hamidur.springBootRESTfulWebservices.utils.recomender;

public class RecommenderReviews {

    private Integer customerId;
    private Integer flightId;
    private Integer score;

    public RecommenderReviews (Integer customerId, Integer flightId, Integer score){
        this.customerId = customerId;
        this.flightId = flightId;
        this.score = score;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
