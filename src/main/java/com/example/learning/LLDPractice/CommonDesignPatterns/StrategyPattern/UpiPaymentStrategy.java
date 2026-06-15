package com.example.learning.LLDPractice.CommonDesignPatterns.StrategyPattern;

public class UpiPaymentStrategy implements PaymentStrategy{

    @Override
    public void pay() {
        System.out.println("Upi payment");
    }
}
