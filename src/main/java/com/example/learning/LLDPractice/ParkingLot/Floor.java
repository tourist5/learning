package com.example.learning.LLDPractice.ParkingLot;

import java.util.List;
import java.util.Optional;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public Floor(int floorNumber,List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public Optional<ParkingSpot> findSpot(Vehicle vehicle) {
        return spots.stream().filter(s->!s.isOccupied())
                .filter(s->s.canFit(vehicle))
                .findFirst();
    }
}
