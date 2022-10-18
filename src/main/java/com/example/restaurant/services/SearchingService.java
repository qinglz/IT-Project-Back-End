package com.example.restaurant.services;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;

import java.util.List;

public interface SearchingService {
    public List<Restaurant> getAllRestaurant();
    public Result findRestaurantByName(String name);
}
