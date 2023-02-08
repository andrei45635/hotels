package com.example.hotels.repository.db;

import com.example.hotels.domain.Location;
import com.example.hotels.repository.IRepository;
import com.example.hotels.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationRepoDB implements IRepository<Integer, Location> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();
    @Override
    public List<Location> findAll() {
        List<Location> locations = new ArrayList<>();
        String query = "SELECT * FROM locations";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                double locID = resultSet.getDouble("locid");
                String locName = resultSet.getString("locname");

                Location location = new Location(locID, locName);
                locations.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

    public Location getLocation(String locName){
        String query = "SELECT * FROM locations WHERE locname = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, locName);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                double locationID = resultSet.getDouble("locid");
                String locationName = resultSet.getString("locname");

                return new Location(locationID, locationName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
