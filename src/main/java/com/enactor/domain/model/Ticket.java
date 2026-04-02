package com.enactor.domain.model;

public class Ticket {

    private String seatReference;

    private double distance;

    private double price;

    public String getSeatReference() {
        return seatReference;
    }

    public void setSeatReference(String seatReference) {
        this.seatReference = seatReference;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
