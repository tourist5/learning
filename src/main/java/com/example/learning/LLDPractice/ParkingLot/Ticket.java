package com.example.learning.LLDPractice.ParkingLot;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final LocalDateTime entryTime;

    public Ticket(String ticketId,
                  Vehicle vehicle,
                  ParkingSpot parkingSpot,
                  LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
    }
}
