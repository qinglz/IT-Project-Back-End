package com.example.restaurant.services.servicesImp;

import com.example.restaurant.services.SearchingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SearchingServiceImpTest {
    @Autowired
    SearchingService searchingService;

    @Test
    void getAllRestaurant() {
        System.out.println(searchingService.getAllRestaurant());


    }

    @Test
    void findRestaurantByName() {
        System.out.println(searchingService.findRestaurantByName("Chicken"));
    }
}