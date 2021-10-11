package dataBaseAccess;

import models.Vehicle;

import java.sql.SQLException;

public interface VehicleDao {
    void save(Vehicle vehicle) throws SQLException;

}
