package org.example.wedding;

import java.time.LocalDate;


public class Wedding {
    // obtain the couple class
    private Couple couple;

    // initialize the date field
    private LocalDate weddingDate;
    // initialize the location field
    private String location;

    // org.example.wedding.Wedding Data Constructor
    public Wedding(Couple couple, LocalDate weddingDate, String location) {
        this.couple = couple;
        this.weddingDate = weddingDate;
        this.location = location;
    }


    // Getter method for the couple
    public Couple getCouple() {
        return couple;
    }
    // Getter method for the Date

    public LocalDate getWeddingDate() {
        return weddingDate;
    }

    // Getter method for Location
    public String getLocation() {
        return location;
    }
}
