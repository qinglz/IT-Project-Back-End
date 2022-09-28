package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RestaurantManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RestaurantManageController {

    @Autowired
    RestaurantManageService registrationService;


    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param restaurantInfo {"restName":value,"address":value,"capacity":value,"numStaff":value}
     * @return Result show if create a new restaurant successfully.
     */
    @PostMapping("/registration")
    public Result registerRestaurant(@RequestBody Map<String,String> restaurantInfo){
        return registrationService.restaurantRegister(restaurantInfo);

    }
}
