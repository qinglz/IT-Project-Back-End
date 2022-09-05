package com.example.restaurant.services;

import com.example.restaurant.pojo.Restaurant;

import java.util.List;

public interface SearchingService {
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> findRestaurantByName(String name);
}
