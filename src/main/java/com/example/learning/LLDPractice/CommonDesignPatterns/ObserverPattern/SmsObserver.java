package com.example.learning.LLDPractice.CommonDesignPatterns.ObserverPattern;

public class SmsObserver implements ObserverCustom{

    @Override
    public void notifyCustom() {
        System.out.println("send sms");
    }
}
