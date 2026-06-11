package com.example.learning.LLDPractice.CommonDesignPatterns.StrategyPattern;

public class PaytmPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay() {
        System.out.println("Paytm Payment");
    }
}
