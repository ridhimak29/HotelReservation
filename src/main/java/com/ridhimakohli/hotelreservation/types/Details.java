package com.ridhimakohli.hotelreservation.types;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Details {

    private String checkInDate;
    private String checkOutDate;
    private String people;
    private Room room;
    private User user;
    private Amount amount;
    private String amenities;


    public Details(String checkInDate, String checkOutDate, String people, Room room, User user, Amount amount, String amenities) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.people = people;
        this.room = room;
        this.user = user;
        this.amount = amount;
        this.amenities = amenities;
    }

    public Details(){}

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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Details)) return false;
        Details details = (Details) o;
        return Objects.equals(checkInDate, details.checkInDate) && Objects.equals(checkOutDate, details.checkOutDate) && Objects.equals(people, details.people) && Objects.equals(room, details.room) && Objects.equals(user, details.user) && Objects.equals(amount, details.amount) && Objects.equals(amenities, details.amenities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkInDate, checkOutDate, people, room, user, amount, amenities);
    }
}
