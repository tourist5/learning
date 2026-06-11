package com.example.learning.LLDPractice.CommonDesignPatterns.FactoryPattern;

public class FactoryPattern {
    public static Vehicle giveObject(String type) throws Exception {
        if("Car".equals(type)) {
            return new Car();
        } else if("Bike".equals(type)) {
            return new Bike();
        }
        throw new Exception("there is no supported");
    }
}
