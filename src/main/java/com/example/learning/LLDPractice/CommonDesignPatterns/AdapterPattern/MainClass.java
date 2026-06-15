package com.example.learning.LLDPractice.CommonDesignPatterns.AdapterPattern;

public class MainClass {
    public static void main(String[] args) {
        UpiPayment upiPayment = new UpiPayment();
        PaymentProcessor upiAdaptor = new UpiAdaptor(upiPayment);
        CheckoutService checkoutService = new CheckoutService(upiAdaptor);
        checkoutService.checkout();

        PayPalPayment payPalPayment = new PayPalPayment();
        PayPalAdaptor payPalAdaptor = new PayPalAdaptor(payPalPayment);
        CheckoutService checkoutService1 = new CheckoutService(payPalAdaptor);
        checkoutService1.checkout();
    }
}
