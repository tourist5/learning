package com.example.learning.LLDPractice.CommonDesignPatterns.ObserverPattern;

public class EmailObserver implements ObserverCustom{

    @Override
    public void notifyCustom() {
        System.out.println("send email");
    }
}
