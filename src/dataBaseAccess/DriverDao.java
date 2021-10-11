package dataBaseAccess;

import models.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverDao {
    void save(Driver driver) throws SQLException;

    Driver findByNationalCode(String nationalCode) throws SQLException;
    Driver findByDriverId(int id) throws SQLException;
    List<Driver> getDriverList() throws SQLException;
}
