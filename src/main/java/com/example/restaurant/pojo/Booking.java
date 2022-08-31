package com.example.restaurant.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document("Booking")
public class Booking {
    private String restaurantName;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String email;
    private ZonedDateTime dateTime;
    private int numPeople;
    private String occasion;
    private String specialConsideration;



    public Booking(String restaurantName, String firstName, String lastName, int phoneNumber,
                   String email, ZonedDateTime dateTime, int numPeople, String occasion, String specialConsideration) {
        this.restaurantName = restaurantName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateTime = dateTime;
        this.numPeople = numPeople;
        this.occasion = occasion;
        this.specialConsideration = specialConsideration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getSpecialConsideration() {
        return specialConsideration;
    }

    public void setSpecialConsideration(String specialConsideration) {
        this.specialConsideration = specialConsideration;
    }
}
