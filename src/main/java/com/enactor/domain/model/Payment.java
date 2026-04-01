package com.enactor.domain.model;

import java.util.UUID;

//Invoice
public class Payment {

    private UUID id;

    private UUID bookingId;

    private double amount;

    private String invoiceReference;

    public Payment(){

    }

    public Payment(UUID bookingId){
        this.bookingId = bookingId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInvoiceReference() {
        return invoiceReference;
    }

    public void setInvoiceReference(String invoiceReference) {
        this.invoiceReference = invoiceReference;
    }
}
