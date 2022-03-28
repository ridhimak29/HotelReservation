package com.ridhimakohli.hotelreservation.types;

public class Amount {

    private double price;
    private long noOfNights;
    private double roomTotal;
    private double tax;
    private double total;

    public Amount(double price, long noOfNights, double roomTotal, double tax, double total) {
        this.price = price;
        this.noOfNights = noOfNights;
        this.roomTotal = roomTotal;
        this.tax = tax;
        this.total = total;
    }

    public Amount(){}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(long noOfNights) {
        this.noOfNights = noOfNights;
    }

    public double getRoomTotal() {
        return roomTotal;
    }

    public void setRoomTotal(double roomTotal) {
        this.roomTotal = roomTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
