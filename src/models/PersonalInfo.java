package models;

import java.sql.Date;

public class PersonalInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String phoneNumber;
    private boolean travelStatus = false;
    private Date birthday;

    public PersonalInfo(String firstName, String lastName, String nationalCode, String phoneNumber, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    public PersonalInfo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
