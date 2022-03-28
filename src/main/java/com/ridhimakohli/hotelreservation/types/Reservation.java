package com.ridhimakohli.hotelreservation.types;


import javax.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name = "RESERVATION_NUMBER")
    private String confirmationNumber;

    @Column(name="CUSTOMER_ID")
    private Long customerId;

    @Column(name="ROOM_ID")
    private Long roomId;

    @Column(name="GUEST_ID")
    private Long guestId;

    @Column(name="Payment_ID")
    private long paymentId;

    @Column(name="CHECK_IN_DATE")
    private String checkInDate;

    @Column(name="CHECK_OUT_DATE")
    private String checkOutDate;

    @Column(name="NO_OF_GUEST")
    private int people;


    public Reservation(String confirmationNumber, Long customerId, Long roomId, Long guestId, long paymentId, String checkInDate, String checkOutDate, int people) {
        this.confirmationNumber = confirmationNumber;
        this.customerId = customerId;
        this.roomId = roomId;
        this.guestId = guestId;
        this.paymentId = paymentId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.people = people;
    }

    public Reservation(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
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

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
