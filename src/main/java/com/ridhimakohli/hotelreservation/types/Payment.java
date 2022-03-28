package com.ridhimakohli.hotelreservation.types;


import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name="NAME_ON_CARD")
    private String cardName;
    @Column(name="CARD_NUMBER")
    private long cardNumber;
    @Column(name="EXPIRY_DATE")
    private String expiryDate;
    @Column(name="AMOUNT")
    private double amount;


    public Payment(long id, String cardName, long cardNumber, double amount, String expiryDate) {
        this.id = id;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.expiryDate = expiryDate;
    }

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
