package com.enactor.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BookingRequestDTO {

    private int seatsCount;

    private UUID customerId;

    private LocalDateTime tripDate;

    private List<String> seatReference;

    private UUID originSegmentId;

    private UUID destinationSegmentId;

    private Object paymentDetails;


    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public List<String> getSeatReference() {
        return seatReference;
    }

    public void setSeatReference(List<String> seatReference) {
        this.seatReference = seatReference;
    }

    public UUID getOriginSegmentId() {
        return originSegmentId;
    }

    public void setOriginSegmentId(UUID originSegmentId) {
        this.originSegmentId = originSegmentId;
    }

    public UUID getDestinationSegmentId() {
        return destinationSegmentId;
    }

    public void setDestinationSegmentId(UUID destinationSegmentId) {
        this.destinationSegmentId = destinationSegmentId;
    }

    public Object getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Object paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}
