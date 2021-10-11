package dataBaseAccess;

import models.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehicleDaoImp extends DataBaseAccess implements VehicleDao {

    public VehicleDaoImp() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void save(Vehicle vehicle) throws SQLException {
        if (getConnection() != null) {


            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "insert into vehicle (vehicle_number,vehicle_color,type_vehicle,driverId,latitude,longitude)" +
                            "values (?,?,?,?,?,?); ", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, vehicle.getCarNumber());
            preparedStatement.setString(2, vehicle.getCarColor());
            preparedStatement.setString(3, vehicle.getTypeVehicle());
            preparedStatement.setInt(4, vehicle.getDriverId());
            preparedStatement.setDouble(5,vehicle.getLatitude());
            preparedStatement.setDouble(6,vehicle.getLongitude());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                vehicle.setId(generatedKey);
            }
        }
    }
}
