package org.sawcha.model;

public class ParkingSpot {
    private int id;
    private VehicleType vehicleType;

    private String vehicleLicense;

    private boolean isParkingSpotAssignedFlag = false;

    public ParkingSpot(int id, VehicleType vehicleType, String vehicleLicense) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicleLicense = vehicleLicense;
    }

    public boolean isOccupied() {
        return isParkingSpotAssignedFlag;
    }

    public void assign(Customer customer) {
        this.vehicleLicense = customer.getLicense();
        this.isParkingSpotAssignedFlag = true;
    }

    public void release() {
        this.vehicleLicense = null;
        this.isParkingSpotAssignedFlag = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }

    public boolean isParkingSpotAssignedFlag() {
        return isParkingSpotAssignedFlag;
    }

    public void setParkingSpotAssignedFlag(boolean parkingSpotAssignedFlag) {
        this.isParkingSpotAssignedFlag = parkingSpotAssignedFlag;
    }
}
