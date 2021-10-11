package models;

import enumeration.DriverStatus;

import java.sql.Date;

public class Driver extends PersonalInfo {
    boolean approveReceiveMoney;
    private DriverStatus driverStatus;

    public Driver() {
        super();
    }

    public Driver(String firstName, String lastName, String nationalCode, String phoneNumber, Date birthday) {
        super(firstName, lastName, nationalCode, phoneNumber, birthday);
    }

    public boolean isApproveReceiveMoney() {
        return approveReceiveMoney;
    }

    public void setApproveReceiveMoney(boolean approveReceiveMoney) {
        this.approveReceiveMoney = approveReceiveMoney;
    }

    @Override
    public String toString() {
        return "Driver{ "
                + getId() + ", "
                + getFirstName() + ", "
                + getLastName() + ", "
                + getNationalCode() + ", "
                + getPhoneNumber() + ", "
                + getBirthday() + " }";
    }
}
