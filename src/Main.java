
import exception.MyCustomException;
import models.Passenger;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TaxiSystem taxiSystem = new TaxiSystem();

    public static void main(String[] args) {
        outer:
        while (true) {
            try {
                String choice;
                shoMainMenu();
                choice = scanner.nextLine().trim();
                if (taxiSystem.isValidChoice(choice, 9)) {
                    switch (choice) {
                        case "1":
                            addGroupOfDrivers();
                            break;
                        case "2":
                            addGroupOfPassengers();
                            break;
                        case "3":
                            driverSignUpOrLogin();
                            break;
                        case "4":
                            passengerSignUpOrLogin();
                            break;
                        case "5":
                            break;
                        case "6":
                            taxiSystem.showDriverList();
                            break;
                        case "7":
                            taxiSystem.showPassengerList();
                            break;
                        case "8":
                            break outer;
                    }
                }
            } catch (MyCustomException exception) {
                System.out.println(exception.getMessage() + "\ntry again");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    private static void shoMainMenu() {
        System.out.println("***Cab Request System*** \n" +
                "1) Add a group of drivers \n" +
                "2) Add a group of passengers \n" +
                "3) Driver signup or login \n" +
                "4) Passenger signup or login \n" +
                "5) Show ongoing travels \n" +
                "6) Show a list of drivers \n" +
                "7) Show a list of passengers \n" +
                "8) exit");
    }

    public static void addGroupOfDrivers() throws SQLException, MyCustomException {
        System.out.println("enter number of drivers u wanna add: ");
        String driverNumbers = scanner.nextLine().trim();
        if (taxiSystem.isNumeric(driverNumbers, "Driver Numbers")) {
            for (int i = 0; i < Integer.parseInt(driverNumbers); i++) {
                addNewDriver(null);
            }
        }
    }

    public static void addGroupOfPassengers() throws MyCustomException, SQLException {
        System.out.println("enter number of Passengers u wanna add: ");
        String passengerNumbers = scanner.nextLine().trim();
        if (taxiSystem.isNumeric(passengerNumbers, "Passenger Numbers")) {
            for (int i = 0; i < Integer.parseInt(passengerNumbers); i++) {
                addNewPassenger(null);
            }
        }
    }

    private static void addNewDriver(String nationalCode) throws MyCustomException, SQLException {
        String[] driverInputFeatures = getDriverAndPassengerInfos(nationalCode);
        String[] carFeatures = getDriverCarFeatures();
        taxiSystem.saveDrivers(driverInputFeatures, carFeatures);
    }

    private static void addNewPassenger(String nationalCode) throws MyCustomException, SQLException {
        String[] passengerInputFeatures = getDriverAndPassengerInfos(nationalCode);
        taxiSystem.savePassengers(passengerInputFeatures);
    }

    public static String[] getDriverAndPassengerInfos(String nationalCode) throws MyCustomException {
        String[] driverPassengerInputs = new String[5];
        System.out.println("enter firstName");
        String firstName = scanner.nextLine().trim();
        System.out.println("enter lastName");
        String lastName = scanner.nextLine().trim();
        if (nationalCode == null) {
            System.out.println("enter nationalCode");
            nationalCode = scanner.nextLine().trim();
        }
        System.out.println("enter phoneNumber");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("enter birthday in yyyy-mm-dd ");
        String birthday = scanner.nextLine().trim();
        driverPassengerInputs[0] = firstName;
        driverPassengerInputs[1] = lastName;
        driverPassengerInputs[2] = nationalCode;
        driverPassengerInputs[3] = phoneNumber;
        driverPassengerInputs[4] = birthday;
        return driverPassengerInputs;
    }

    public static String[] getDriverCarFeatures() throws MyCustomException {
        String[] carFeatures = new String[5];
        System.out.println("enter your car info: ");
        System.out.println("Vehicle number: ");
        String carNum = scanner.nextLine().trim();
        System.out.println("type Of Vehicle: ");
        String typeVehicle = scanner.nextLine().trim();
        System.out.println("color of Vehicle: ");
        String vehicleColor = scanner.nextLine().trim();
        System.out.println("your latitude: ");
        String latitude = scanner.nextLine().trim();
        System.out.println("your longitude: ");
        String longitude = scanner.nextLine().trim();
        carFeatures[0] = carNum;
        carFeatures[1] = typeVehicle;
        carFeatures[2] = vehicleColor;
        carFeatures[3] = latitude;
        carFeatures[4] = longitude;
        return carFeatures;
    }


    public static void passengerSignUpOrLogin() throws SQLException, MyCustomException {
        System.out.println("nationalCode: ");
        String nationalCode = scanner.nextLine().trim();
        if (taxiSystem.isNumeric(nationalCode, "national code")) {
            if (!taxiSystem.isExistPassenger(nationalCode)) {
                subMenuPassenger(nationalCode);
            } else {
                System.out.println("registered before");
                requestCab(nationalCode);
            }
        }
    }

    public static void subMenuPassenger(String nationalCode) throws MyCustomException, SQLException {
        while (true) {
            System.out.println("1) register \n" + "2) exit");
            String choice = scanner.nextLine().trim();
            if (taxiSystem.isValidChoice(choice, 3)) {
                if (choice.equalsIgnoreCase("1")) {
                    addNewPassenger(nationalCode);
                    requestCab(nationalCode);
                } else if (choice.equalsIgnoreCase("2")) {
                    break;
                }
            }
        }
    }

    public static void driverSignUpOrLogin() throws MyCustomException, SQLException {
        System.out.println("nationalCode: ");
        String nationalCode = scanner.nextLine().trim();
        if (taxiSystem.isNumeric(nationalCode, "national code")) {
            if (!taxiSystem.isExistDriver(nationalCode)) {
                subMenuDriver(nationalCode);
            } else {
                System.out.println("registered before");
            }
        }
    }

    public static void subMenuDriver(String nationalCode) throws MyCustomException, SQLException {
        while (true) {
            System.out.println("1) register \n" + "2) exit");
            String choice = scanner.nextLine().trim();
            if (taxiSystem.isValidChoice(choice, 3)) {
                if (choice.equalsIgnoreCase("1")) {
                    addNewDriver(nationalCode);
                } else if (choice.equalsIgnoreCase("2")) {
                    break;
                }
            }
        }
    }

    public static void requestCab(String passengerNationalCode) throws MyCustomException, SQLException {
        while (true) {
            if (!taxiSystem.passengerTripStatus(passengerNationalCode)) {
                System.out.println("1)request Cab By Cache\n" +
                        "2)request cab by account\n" +
                        "3) increase account money\n" +
                        "4) exit");
            }
            String choice = scanner.nextLine().trim();
            if (taxiSystem.isValidChoice(choice, 5)) {
                if (choice.equalsIgnoreCase("1")) {
                    System.out.println("enter destination latitude");
                    String latitude = scanner.nextLine().trim();
                    System.out.println("enter destination longitude");
                    String longitude = scanner.nextLine().trim();
                    taxiSystem.requestCabByCache(passengerNationalCode,latitude,longitude);
                } else if (choice.equalsIgnoreCase("2")) {
                    System.out.println("enter destination latitude");
                    String latitude = scanner.nextLine().trim();
                    System.out.println("enter destination longitude");
                    String longitude = scanner.nextLine().trim();
                    taxiSystem.requestCabByAccount(passengerNationalCode,latitude,longitude);
                } else if (choice.equalsIgnoreCase("3")) {
                    System.out.println("enter deposit u wanna add ");
                    String deposit = scanner.nextLine().trim();
                    taxiSystem.increaseAccountDeposit(passengerNationalCode,deposit);
                } else if (choice.equalsIgnoreCase("4")) {
                    break;
                }
            }
        }
    }
}
