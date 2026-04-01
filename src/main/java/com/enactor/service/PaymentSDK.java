package com.enactor.service;

import com.enactor.domain.model.Payment;

import java.util.UUID;

/**
 * demonstrates payments API calls performed through a SDK or standalone module
 */
public class PaymentSDK {

    public Payment createPayment(){
        Payment payment = new Payment();
        payment.setInvoiceReference(UUID.randomUUID().toString());
        return payment;
    }
}
