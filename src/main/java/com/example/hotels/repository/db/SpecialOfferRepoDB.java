package com.example.hotels.repository.db;

import com.example.hotels.domain.SpecialOffer;
import com.example.hotels.repository.IRepository;
import com.example.hotels.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SpecialOfferRepoDB implements IRepository<Integer, SpecialOffer> {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<SpecialOffer> findAll() {
        List<SpecialOffer> specialOffers = new ArrayList<>();
        String query = "SELECT * FROM specialoffers";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while(resultSet.next()){
                double spID = resultSet.getDouble("specialofferid");
                double hotelID = resultSet.getDouble("hotelid");
                LocalDate start = resultSet.getDate("startdate").toLocalDate();
                LocalDate end = resultSet.getDate("enddate").toLocalDate();
                int percents = resultSet.getInt("percents");

                SpecialOffer specialOffer = new SpecialOffer(spID, hotelID, start, end, percents);
                specialOffers.add(specialOffer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialOffers;
    }

    public List<SpecialOffer> findAllWithinADate(double hotelID, LocalDate start, LocalDate end){
        List<SpecialOffer> specialOffers = new ArrayList<>();
        String query = "SELECT * FROM specialoffers WHERE hotelID = ? AND startdate >= ? AND enddate <= ?";
        try(Connection connection = jdbcUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setDouble(1, hotelID);
            statement.setDate(2, Date.valueOf(start));
            statement.setDate(3, Date.valueOf(end));
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                double spID = resultSet.getDouble("specialofferid");
                double hotID = resultSet.getDouble("hotelid");
                LocalDate startDate = resultSet.getDate("startdate").toLocalDate();
                LocalDate endDate = resultSet.getDate("enddate").toLocalDate();
                int percents = resultSet.getInt("percents");

                SpecialOffer specialOffer = new SpecialOffer(spID, hotID, startDate, endDate, percents);
                specialOffers.add(specialOffer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialOffers;
    }
}
