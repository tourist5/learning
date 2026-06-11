package com.example.learning.LLDPractice.CommonDesignPatterns.AdapterPattern;

public class UpiAdaptor implements PaymentProcessor{
    private UpiPayment upiPayment;
    UpiAdaptor(UpiPayment upiPayment) {
        this.upiPayment = upiPayment;
    }
    @Override
    public void pay() {
        upiPayment.makePayment();
    }
}
