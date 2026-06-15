package com.example.learning.LLDPractice.ParkingLot;

public class BikePricing implements PricingStrategy{

    @Override
    public double calculateFee(long hours) {
        return hours*10;
    }
}
