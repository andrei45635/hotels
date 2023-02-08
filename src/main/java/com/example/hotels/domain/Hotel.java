package com.example.hotels.domain;

public class Hotel extends Entity<Double>{
    private double locID;
    private String hotelName;
    private int noRooms;
    private double pricePerNight;
    private GuestType type;

    public Hotel(double hotelID, double locID, String hotelName, int noRooms, double pricePerNight, GuestType type) {
        this.id = hotelID;
        this.locID = locID;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public double getLocID() {
        return locID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public GuestType getType() {
        return type;
    }
}
