package com.example.learning.LLDPractice.CommonDesignPatterns.ObserverPattern;

public class Client {
    public static void main(String[] args) {
        Product product = new Product();
        EmailObserver emailObserver = new EmailObserver();
        product.addObserver(emailObserver);
        product.setStock(4);
        product.addObserver(new SmsObserver());
        product.setStock(10);
        product.removeObserver(emailObserver);
        product.setStock(12);
    }
}
