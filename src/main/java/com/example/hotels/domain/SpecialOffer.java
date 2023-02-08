package com.example.hotels.domain;

import java.time.LocalDate;

public class SpecialOffer extends Entity<Double>{
    private double hotelID;
    private LocalDate start;
    private LocalDate end;
    private int percents;

    public SpecialOffer(double specialOfferID, double hotelID, LocalDate start, LocalDate end, int percents) {
        this.id = specialOfferID;
        this.hotelID = hotelID;
        this.start = start;
        this.end = end;
        this.percents = percents;
    }

    public SpecialOffer(double hotelID, LocalDate start, LocalDate end, int percents) {
        this.hotelID = hotelID;
        this.start = start;
        this.end = end;
        this.percents = percents;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public int getPercents() {
        return percents;
    }
}
