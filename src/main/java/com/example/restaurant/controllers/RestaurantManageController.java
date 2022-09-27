package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RestaurantManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantManageController {

    @Autowired
    RestaurantManageService registrationService;

    @PostMapping("/registration")
    public Result registerRestaurant(@RequestBody Restaurant restaurant){
        return registrationService.restaurantRegister(restaurant);

    }
}
