package models;

import java.util.Objects;

public class Vehicle {
    private int id;
    private int carNumber;
    private String typeVehicle;
    private String carColor;
    private double latitude;
    private double longitude;
    private int driverId;

    public Vehicle(int carNumber, String typeVehicle, String carColor, int driverId, double latitude, double longitude) {
        this.carNumber = carNumber;
        this.typeVehicle = typeVehicle;
        this.carColor = carColor;
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getDriverId() {
        return driverId;
    }

    public Vehicle() {
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }


    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "vehicle{ "
                + getCarNumber() + ", "
                + getTypeVehicle() + ", "
                + getCarColor() + ", "
                + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
