package com.example.learning.LLDPractice.CommonDesignPatterns.StrategyPattern;

public class Client {
    public static void main(String[] args) {
        UpiPaymentStrategy upiPaymentStrategy = new UpiPaymentStrategy();
        PaymentService paymentService = new PaymentService(upiPaymentStrategy);
        paymentService.pay();
        PaytmPaymentStrategy paytmPaymentStrategy = new PaytmPaymentStrategy();
        PaymentService paymentService1 = new PaymentService(paytmPaymentStrategy);
        paymentService1.pay();
    }
}
