package com.enactor.domain.dto;

import java.util.List;

public class BookingAvailabilityResponse {

    private double totalPrice;

    private int availableSeatsCount;

    private List<String> seatReferences;

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getSeatReferences() {
        return seatReferences;
    }

    public void setSeatReferences(List<String> seatReferences) {
        this.seatReferences = seatReferences;
    }

    public int getAvailableSeatsCount() {
        return availableSeatsCount;
    }

    public void setAvailableSeatsCount(int availableSeatsCount) {
        this.availableSeatsCount = availableSeatsCount;
    }
}
