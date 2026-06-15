package com.example.learning.LLDPractice.ParkingLot;

public class PricingStrategyFactory {
    public PricingStrategy getStrategy(Vehicle vehicle) {
        return switch (vehicle.getVehicleType()) {
            case CAR -> new CarPricing();
            case BIKE -> new BikePricing();
            case TRUCK -> new TruckPricing();
        };
    }
}
