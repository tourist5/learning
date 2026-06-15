package com.example.learning.LLDPractice.ParkingLot;

public class BikeParkingSpot extends ParkingSpot{
    BikeParkingSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFit(Vehicle vehicle) {
        return vehicle.getVehicleType()==VehicleType.BIKE;
    }
}
