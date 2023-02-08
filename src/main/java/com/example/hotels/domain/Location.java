package com.example.hotels.domain;

public class Location extends Entity<Double>{
    private String name;

    public Location(Double locID, String name) {
        this.id = locID;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
