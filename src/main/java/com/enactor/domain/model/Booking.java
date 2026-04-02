package com.enactor.domain.model;

import com.enactor.domain.enums.BookingStatusEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {

    private UUID id;

    private LocalDateTime createdDateTime;

    private LocalDateTime bookingDateTime;

    private List<Ticket> tickets;

    private BookingStatusEnum status;

    private Payment payment;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public BookingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookingStatusEnum status) {
        this.status = status;
    }
}
