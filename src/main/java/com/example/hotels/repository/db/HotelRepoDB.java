package com.example.hotels.repository.db;

import com.example.hotels.domain.GuestType;
import com.example.hotels.domain.Hotel;
import com.example.hotels.repository.IRepository;
import com.example.hotels.utils.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRepoDB implements IRepository<Integer, Hotel> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                double hotelID = resultSet.getDouble("hotelid");
                double locID = resultSet.getDouble("locid");
                String hotelName = resultSet.getString("hotelname");
                int noRooms = resultSet.getInt("norooms");
                double pricePerNight = resultSet.getDouble("pricepernight");
                GuestType type = GuestType.valueOf(resultSet.getString("guesttype"));

                Hotel hotel = new Hotel(hotelID, locID, hotelName, noRooms, pricePerNight, type);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }

    public List<Hotel> findHotelsLoc(double locID){
        List<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM hotels WHERE locid = ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, locID);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                double hotelID = resultSet.getDouble("hotelid");
                double locationID = resultSet.getDouble("locid");
                String hotelName = resultSet.getString("hotelname");
                int noRooms = resultSet.getInt("norooms");
                double pricePerNight = resultSet.getDouble("pricepernight");
                GuestType type = GuestType.valueOf(resultSet.getString("guesttype"));

                Hotel hotel = new Hotel(hotelID, locationID, hotelName, noRooms, pricePerNight, type);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }
}
