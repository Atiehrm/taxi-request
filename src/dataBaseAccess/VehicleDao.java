package dataBaseAccess;

import models.Driver;
import models.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
    void save(Vehicle vehicle) throws SQLException;
    List<Vehicle> getVehicleList() throws SQLException;

}
