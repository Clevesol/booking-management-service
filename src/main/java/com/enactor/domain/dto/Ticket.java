package com.enactor.domain.dto;

import java.util.UUID;

public class Ticket {

    private UUID ticketId;

    private String seatReference;

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatReference() {
        return seatReference;
    }

    public void setSeatReference(String seatReference) {
        this.seatReference = seatReference;
    }
}
