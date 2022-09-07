package com.example.restaurant.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("BusinessUser")
public class BusinessUser {


    @Id
    private String id;
    private String name;
    private Restaurant restaurantName;
    private String email;
    private String password;
    public BusinessUser(String id, Restaurant restaurantName, String email, String password) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.email = email;
        this.password = password;
    }

    public BusinessUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public BusinessUser() {
    }

    @Override
    public String toString() {
        return "BusinessUser{" +
                "id='" + id + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurantName() {
        return restaurantName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRestaurantName(Restaurant restaurantName) {
        this.restaurantName = restaurantName;
    }

}
