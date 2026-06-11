package com.example.learning.LLDPractice.ParkingLot;

public class TruckParkingSpot extends ParkingSpot{
    TruckParkingSpot(String id) {
        super(id);
    }

    @Override
    public boolean canFit(Vehicle vehicle) {
        return vehicle.getVehicleType()==VehicleType.TRUCK;
    }

}
