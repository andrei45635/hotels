package com.example.hotels.service;

import com.example.hotels.domain.Hotel;
import com.example.hotels.domain.Location;
import com.example.hotels.domain.SpecialOffer;
import com.example.hotels.repository.db.HotelRepoDB;
import com.example.hotels.repository.db.LocationRepoDB;
import com.example.hotels.repository.db.SpecialOfferRepoDB;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
    private LocationRepoDB locationRepoDB;
    private HotelRepoDB hotelRepoDB;
    private SpecialOfferRepoDB specialOfferRepoDB;

    public Service(LocationRepoDB locationRepoDB, HotelRepoDB hotelRepoDB, SpecialOfferRepoDB specialOfferRepoDB) {
        this.locationRepoDB = locationRepoDB;
        this.hotelRepoDB = hotelRepoDB;
        this.specialOfferRepoDB = specialOfferRepoDB;
    }

    public Location getLocationByName(String locName){
        return locationRepoDB.getLocation(locName);
    }

    public List<Location> findAllLocations(){
        return locationRepoDB.findAll();
    }

    public Set<String> getAllLocations(){
        Set<String> allLocs = new HashSet<>();
        for(Location loc: this.findAllLocations()){
            allLocs.add(loc.getName());
        }
        return allLocs;
    }

    public List<Hotel> getHotelsForLocation(double locID){
        return hotelRepoDB.findHotelsLoc(locID);
    }

    public List<SpecialOffer> getAllOffersForDates(double hotelID, LocalDate start, LocalDate end){
        return specialOfferRepoDB.findAllWithinADate(hotelID, start, end);
    }
}
