package com.example.learning.LLDPractice.ParkingLot;

public abstract class ParkingSpot {
    protected final String id;
    protected boolean occupied;
    protected ParkingSpot(String id) {
        this.id = id;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void free() {
        occupied = false;
    }

    public String getId() {
        return id;
    }

    public abstract boolean canFit(Vehicle vehicle);

}
