package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/registration")
    public Result registerRestaurant(@RequestBody Restaurant restaurant){
        return registrationService.restaurantRegister(restaurant);

    }
}
