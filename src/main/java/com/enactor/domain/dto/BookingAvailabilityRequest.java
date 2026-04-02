package com.enactor.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingAvailabilityRequest {

    private int passengersCount;
    private UUID origin;
    private UUID destination;
    private LocalDateTime tripDateTime;

    public int getPassengersCount() {
        return passengersCount;
    }

    public void setPassengersCount(int passengersCount) {
        this.passengersCount = passengersCount;
    }

    public UUID getOrigin() {
        return origin;
    }

    public void setOrigin(UUID origin) {
        this.origin = origin;
    }

    public UUID getDestination() {
        return destination;
    }

    public void setDestination(UUID destination) {
        this.destination = destination;
    }

    public LocalDateTime getTripDateTime() {
        return tripDateTime;
    }

    public void setTripDateTime(LocalDateTime tripDateTime) {
        this.tripDateTime = tripDateTime;
    }
}
