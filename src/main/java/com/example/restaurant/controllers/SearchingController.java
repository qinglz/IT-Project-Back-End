package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.services.SearchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchingController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    SearchingService searchingService;


    @GetMapping("/allRestaurants")
    public Result<List<Restaurant>> getAllRestaurant(){
        return Result.success(searchingService.getAllRestaurant());

    }

    @GetMapping("/findRestaurantByName")
    public Result<List<Restaurant>> getAllRestaurant(@RequestParam("name") String name){
        List<Restaurant> restaurants = searchingService.findRestaurantByName(name);
        if(restaurants.size()>0){
            return Result.success(restaurants);
        }
        return Result.error("No Restaurant Found");




    }

}
