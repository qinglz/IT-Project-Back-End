package com.example.restaurant.pojo;

public class SignUpInfo {
    private String email;
    private String password;

    private String restaurantName;

    public SignUpInfo(String email, String password, String restaurantName) {
        this.email = email;
        this.password = password;
        this.restaurantName = restaurantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
