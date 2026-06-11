package com.example.learning.LLDPractice.ParkingLot;

public class TruckPricing implements PricingStrategy{

    @Override
    public double calculateFee(long hours) {
        return hours*30;
    }
}
