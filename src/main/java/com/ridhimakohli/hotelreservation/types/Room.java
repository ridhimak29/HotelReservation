package com.ridhimakohli.hotelreservation.types;


import com.ridhimakohli.hotelreservation.utils.StringListConverter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String roomName;

    @Column(name = "DESCRIPTION",length = 1000)
    private String description;

    @Column(name= "IMAGE")
    private String image;

    @Column(name = "PRICE")
    private double price;

    @Column(name= "IS_AVAILABLE")
    private boolean available = true;

    @Column(name="AMENITIES")
    @Convert(converter = StringListConverter.class)
    private Set<Amenities> amenities;

    public Room(long id, String roomName, String description, String image, double price, boolean available, Set<Amenities> amenities) {
        this.id = id;
        this.roomName = roomName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.available = available;
        this.amenities = amenities;
    }

    public Room(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenities> amenities) {
        this.amenities = amenities;
    }


}
