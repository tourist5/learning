package com.example.learning.LLDPractice.ParkingLot;

public class CarParkingSpot extends ParkingSpot{
    CarParkingSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFit(Vehicle vehicle) {
        return vehicle.getVehicleType()==VehicleType.CAR;
    }
}
