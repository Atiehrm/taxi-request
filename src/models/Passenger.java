package models;

import java.sql.Date;


public class Passenger extends PersonalInfo {
    private boolean inTrip;
    private double latitudePassenger;
    private double longitudePassenger;

    public Passenger(String firstName, String lastName, String nationalCode, String phoneNumber, Date birthday) {
        super(firstName, lastName, nationalCode, phoneNumber, birthday);
    }

    public Passenger() {

    }

    public boolean isInTrip() {
        return inTrip;
    }

    public void setInTrip(boolean inTrip) {
        this.inTrip = inTrip;
    }

    public double getLatitudePassenger() {
        return latitudePassenger;
    }

    public void setLatitudePassenger(double latitudePassenger) {
        this.latitudePassenger = latitudePassenger;
    }

    public double getLongitudePassenger() {
        return longitudePassenger;
    }

    public void setLongitudePassenger(double longitudePassenger) {
        this.longitudePassenger = longitudePassenger;
    }

    @Override
    public String toString() {
        return "Passengers{ "
                + getId() + ", "
                + getFirstName() + ", "
                + getLastName() + ", "
                + getNationalCode() + ", "
                + getPhoneNumber() + ", "
                + getBirthday() + " }";
    }
}
