package com.example.learning.LLDPractice.CommonDesignPatterns.StrategyPattern;

public class PaymentService implements PaymentStrategy{
    private final PaymentStrategy paymentStrategy;
    PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public void pay() {
        paymentStrategy.pay();
    }
}
