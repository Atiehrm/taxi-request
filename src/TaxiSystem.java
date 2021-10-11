import dataBaseAccess.*;
import enumeration.VehicleType;
import exception.MyCustomException;
import models.Driver;
import models.Passenger;
import models.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TaxiSystem {
    private static DriverDao driverDao;
    private static PassengerDao passengerDao;
    private static VehicleDao vehicleDao;

    static {
        try {
            driverDao = new DriverDaoImp();
            passengerDao = new PassengerDaoImp();
            vehicleDao = new VehicleDaoImp();
        } catch (SQLException | ClassNotFoundException sqlException) {
            System.out.println("cannot connect");
        }
    }

    protected boolean isValidChoice(String choice, int maxNumber) throws MyCustomException {
        if (isNumeric(choice, "choice") && Integer.parseInt(choice) < maxNumber) {
            return true;
        }
        throw new MyCustomException("choice should be less than " + maxNumber);
    }

    protected boolean isValidPhone(String phone) throws MyCustomException {
        if (isNumeric(phone, "phone number") && phone.length() < 13) {
            return true;
        }
        throw new MyCustomException("phone is not valid");
    }

    protected boolean isValidDate(String dateStr) throws MyCustomException {
        try {
            Date date = Date.valueOf(dateStr);
        } catch (Exception exception) {
            throw new MyCustomException("date format is incorrect");
        }
        return true;
    }

    protected boolean isValidTypeVehicle(String typeVehicle) throws MyCustomException {
        VehicleType[] types = VehicleType.values();
        for (VehicleType type : types) {
            if (typeVehicle.equalsIgnoreCase(type.getAbbr())) {
                return true;
            }
        }
        throw new MyCustomException("vehicle type format is incorrect");
    }

    public void saveDrivers(String[] driverInputs
            , String[] carFeature) throws SQLException, MyCustomException {
        if (isValidUserInfo(driverInputs)) {
            if (!isExistDriver(driverInputs[2])) {
                Driver driver = new Driver(driverInputs[0], driverInputs[1]
                        , driverInputs[2], driverInputs[3]
                        , Date.valueOf(driverInputs[4]));
                driverDao.save(driver);
                addCarOfDriver(driver, carFeature);
                System.out.println("driver added successfully ");
            } else {
                System.out.println(driverInputs[2] + " is duplicate");
            }
        }
    }

    public void savePassengers(String[] passengerInputs) throws SQLException, MyCustomException {
        if (isValidUserInfo(passengerInputs)) {
            if (!isExistPassenger(passengerInputs[2])) {
                Passenger passenger = new Passenger(passengerInputs[0], passengerInputs[1]
                        , passengerInputs[2], passengerInputs[3]
                        , Date.valueOf(passengerInputs[4]));
                passengerDao.save(passenger);
                System.out.println("passenger added successfully ");
            } else {
                System.out.println(passengerInputs[2] + " is duplicate");
            }
        }
    }

    public boolean isExistDriver(String nationalCode) throws SQLException {
        Driver found = driverDao.findByNationalCode(nationalCode);
        return found != null;
    }

    public boolean isExistPassenger(String nationalCode) throws SQLException {
        Passenger found = passengerDao.findByNationalCode(nationalCode);
        return found != null;
    }

    private boolean isValidUserInfo(String[] driverInputs) throws MyCustomException {
        return isAlphabetic(driverInputs[0], "first name") && isAlphabetic(driverInputs[1]
                , "lastname")
                && isNumeric(driverInputs[2], "national code")
                && isValidPhone(driverInputs[3]) && isValidDate(driverInputs[4]);
    }

    public void addCarOfDriver(Driver driver, String[] carFeature) throws SQLException, MyCustomException {
        if (isValidVehicleInfo(carFeature)) {
            Vehicle vehicle = new Vehicle(Integer.parseInt(carFeature[0])
                    , carFeature[1], carFeature[2], driver.getId(), Double.parseDouble(carFeature[3])
                    , Double.parseDouble(carFeature[4]));
            vehicleDao.save(vehicle);
        }
    }

    private boolean isValidVehicleInfo(String[] carFeature) throws MyCustomException {
        return isNumeric(carFeature[0], "car number") && isValidTypeVehicle(carFeature[1])
                && isAlphabetic(carFeature[2], "car color")
                && isNumeric(carFeature[3], "latitude")
                && isNumeric(carFeature[4], "longitude");
    }

    public void showDriverList() {
        try {
            List<Driver> driverList = driverDao.getDriverList();
            for (Driver driver : driverList) {
                System.out.println(driver);
            }
        } catch (NullPointerException | SQLException exception) {
            System.out.println(" exception happens" + exception.getMessage());
        }
    }

    public void showPassengerList() {
        try {
            List<Passenger> passengerList = passengerDao.getPassengerList();
            for (Passenger passenger : passengerList) {
                System.out.println(passenger);
            }
        } catch (SQLException exception) {
            System.out.println("exception happens" + exception.getMessage());
        }
    }

    protected boolean isNumeric(String input, String name) throws MyCustomException {
        if (input.matches("[0-9]+")) {
            return true;
        }
        throw new MyCustomException(name + " is not integer number");
    }

    protected boolean isAlphabetic(String input, String name) throws MyCustomException {
        if (input.matches("[a-zA-Z]+")) {
            return true;
        }
        throw new MyCustomException(name + " is not alphabetic");
    }

    protected boolean passengerTripStatus(String nationalCode) throws SQLException {
        Passenger found = passengerDao.findByNationalCode(nationalCode);
        return found.isInTrip();
    }


    protected void requestCabByCache(){
        
    }
    protected void requestCabByAccount(){

    }
    protected void increaseAccountDeposit(){

    }
}
