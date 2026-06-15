package com.example.learning.LLDPractice.ParkingLot;

import java.time.Duration;
import java.time.LocalDateTime;

public class FeeCalculator {
    private final PricingStrategyFactory pricingStrategyFactory = new PricingStrategyFactory();
    public double calculateFee (Ticket ticket,LocalDateTime exitTime) {
        long hours = Duration.between(ticket.getEntryTime(),exitTime).toHours();
        PricingStrategy strategy = pricingStrategyFactory.getStrategy(ticket.getVehicle());
        return strategy.calculateFee(hours);
    }
}
