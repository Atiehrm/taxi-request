package dataBaseAccess;

import models.Driver;
import models.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            preparedStatement.setDouble(5, vehicle.getLatitude());
            preparedStatement.setDouble(6, vehicle.getLongitude());
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                vehicle.setId(generatedKey);
            }
        }
    }

    @Override
    public List<Vehicle> getVehicleList() throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from vehicle ");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setCarNumber(resultSet.getInt("vehicle_number"));
                vehicle.setCarColor(resultSet.getString("vehicle_color"));
                vehicle.setTypeVehicle(resultSet.getString("type_vehicle"));
                vehicle.setDriverId(resultSet.getInt("driverId"));
                vehicle.setLatitude(resultSet.getDouble("latitude"));
                vehicle.setLongitude(resultSet.getDouble("longitude"));
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    @Override
    public Vehicle findByDriverId(int id) throws SQLException {
        Vehicle vehicle = null;
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from vehicle where DriverId= %d", id);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setCarNumber(resultSet.getInt("vehicle_number"));
                vehicle.setCarColor(resultSet.getString("vehicle_color"));
                vehicle.setTypeVehicle(resultSet.getString("type_vehicle"));
                vehicle.setDriverId(resultSet.getInt("driverId"));
                vehicle.setLatitude(resultSet.getDouble("latitude"));
                vehicle.setLongitude(resultSet.getDouble("longitude"));
            }
        }
        return vehicle;
    }
}

