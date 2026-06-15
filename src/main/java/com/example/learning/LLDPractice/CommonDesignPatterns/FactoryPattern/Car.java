package com.example.learning.LLDPractice.CommonDesignPatterns.FactoryPattern;

public class Car implements Vehicle{
    @Override
    public void drive() {
        System.out.println("car");
    }
}
