package com.ridhimakohli.hotelreservation.types.external;

import com.ridhimakohli.hotelreservation.types.Guest;
import com.ridhimakohli.hotelreservation.types.Payment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReservationRequest {

    @NotNull
    Guest guest;
    @NotNull
    Payment payment;
    @NotBlank(message = "customerId can't empty!")
    String customerId;
    @NotBlank(message = "roomId can't empty!")
    String roomId;
    @NotBlank(message = "checkedIn can't empty!")
    String checkInDate;
    @NotBlank(message = "checkedOut can't empty!")
    String checkOutDate;
    @NotBlank(message = "checkedOut can't empty!")
    String people;

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

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
