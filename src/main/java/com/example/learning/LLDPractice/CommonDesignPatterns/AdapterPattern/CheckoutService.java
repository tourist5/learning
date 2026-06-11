package com.example.learning.LLDPractice.CommonDesignPatterns.AdapterPattern;

import com.example.learning.LLDPractice.ParkingLot.ParkingService;

public class CheckoutService{
    private final PaymentProcessor paymentProcessor;
    CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    public void checkout() {
        paymentProcessor.pay();
    }
}
