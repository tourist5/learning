package com.example.learning.LLDPractice.ParkingLot;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingService {
    private final ParkingLot parkingLot;
    private final Lock lock = new ReentrantLock();
    public ParkingService(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
    public Ticket parkVehicle(Vehicle vehicle) {
        lock.lock();
        try {
            var spot = parkingLot.assignSpot(vehicle);
            if(spot.isEmpty()) {
                throw new RuntimeException("Spot not available");
            }
            spot.get().occupy();
            return new Ticket(UUID.randomUUID().toString(),vehicle,spot.get(), LocalDateTime.now());
        } finally {
            lock.unlock();
        }
    }
}
