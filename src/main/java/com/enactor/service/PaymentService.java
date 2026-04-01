package com.enactor.service;

import com.enactor.appcore.appserver.core.annotation.Service;
import com.enactor.domain.model.Payment;

@Service
public class PaymentService {

    private PaymentSDK paymentSDK;

    public Payment createPayment(Payment payment){
        Payment invoice = paymentSDK.createPayment();
        return null;
    }
}
