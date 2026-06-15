package com.example.learning.LLDPractice.CommonDesignPatterns.AdapterPattern;

public class PayPalAdaptor implements PaymentProcessor{
    private final PayPalPayment payPalPayment;
    PayPalAdaptor(PayPalPayment payPalPayment) {
        this.payPalPayment = payPalPayment;
    }
    @Override
    public void pay() {
        payPalPayment.makePaypalPayment();
    }
}
