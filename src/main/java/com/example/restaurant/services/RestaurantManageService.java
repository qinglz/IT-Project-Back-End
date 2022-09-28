package com.example.restaurant.services;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;

import java.util.Map;

public interface RestaurantManageService {
    public Result restaurantRegister(Map<String,String> restaurantInfo);
}
