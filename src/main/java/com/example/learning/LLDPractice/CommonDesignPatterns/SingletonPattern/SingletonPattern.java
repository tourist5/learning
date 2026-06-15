package com.example.learning.LLDPractice.CommonDesignPatterns.SingletonPattern;

public class SingletonPattern {
    private static SingletonPattern instance;
    private SingletonPattern() {

    }
    public static synchronized SingletonPattern createSingleInstance() {
        if(instance==null) {
            instance = new SingletonPattern();
        }
        return instance;
    }

}


