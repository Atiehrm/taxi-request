package dataBaseAccess;

import models.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface PassengerDao {
    void save(Passenger passenger) throws SQLException;

    List<Passenger> getPassengerList() throws SQLException;

    Passenger findByNationalCode(String nationalCode) throws SQLException;
}
