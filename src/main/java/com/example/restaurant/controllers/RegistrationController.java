package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.pojo.SignUpInfo;
import com.example.restaurant.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("registration")
    public void registerRestaurant(@RequestBody SignUpInfo signUpInfo){
        registrationService.restaurantRegister(signUpInfo);

    }
}
