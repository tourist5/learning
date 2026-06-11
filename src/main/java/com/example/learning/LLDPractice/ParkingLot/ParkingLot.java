package com.example.learning.LLDPractice.ParkingLot;

import java.util.List;
import java.util.Optional;

public class ParkingLot {
    private final List<Floor> floors;
    public ParkingLot(List<Floor> floors) {
        this.floors= floors;
    }
    public Optional<ParkingSpot> assignSpot(Vehicle vehicle) {
        for (Floor floor : floors) {
            Optional<ParkingSpot> spot = floor.findSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
