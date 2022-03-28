package com.ridhimakohli.hotelreservation.types.external;


import javax.validation.constraints.NotBlank;

public class SearchRequest {

    @NotBlank(message = "checkedIn can't empty!")
    String checkInDate;

    @NotBlank(message = "checkedOut can't empty!")
    String checkOutDate;

    @NotBlank(message = "checkedOut can't empty!")
    String people;

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
