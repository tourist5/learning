package com.example.learning.LLDPractice.ParkingLot;

public class CarPricing implements PricingStrategy {

    @Override
    public double calculateFee(long hours) {
        return hours*20;
    }
}
