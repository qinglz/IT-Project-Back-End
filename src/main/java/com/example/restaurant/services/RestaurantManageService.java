package com.example.restaurant.services;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface RestaurantManageService {
    public Result restaurantUpdate(Map<String,String> restaurantInfo);
    public Result getAllRestaurants();
    public Result restaurantRegister(Map<String,String> restaurantInfo);
    public Result getUserName();
    public Result restaurantDelete(Map<String,String> restaurantInfo);
    public Result getBookingByRestaurant(String id);
}
