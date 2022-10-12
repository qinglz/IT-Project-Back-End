package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RestaurantManageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * All API in this controller need Token!
 */
@RestController
public class RestaurantManageController {

    @Autowired
    RestaurantManageService restaurantManageService;


    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param restaurantInfo {"restId":value,"restName":value,"address":value,"capacity":value,"numStaff":value}
     * @return Result show if create a new restaurant successfully.
     */
    @PostMapping("/updateRestaurant")
    public Result updateRestaurant(@RequestBody Map<String,String> restaurantInfo){
        return restaurantManageService.restaurantUpdate(restaurantInfo);

    }

    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param restaurantInfo {"restName":value,"address":value,"capacity":value,"numStaff":value}
     * @return Result show if create a new restaurant successfully.
     */
    @PostMapping("/registerRestaurant")
    public Result registerRestaurant(@RequestBody Map<String,String> restaurantInfo){
        return restaurantManageService.restaurantRegister(restaurantInfo);
    }

    /**
     * @return This API will return a list of restaurant information of current user.
     * Please Store The Restaurant ID even it's not shown in website, because updating
     * restaurant need ID to find the restaurant which needed to be updated.
     */
    @GetMapping("/getRestaurantsForUser")
    public Result getRestaurants(){
        return restaurantManageService.getAllRestaurants();
    }

    @GetMapping("/getBookingForRestaurant")
    public Result getBookingForRestaurant(@RequestParam("restId") String restId){
        return restaurantManageService.getBookingByRestaurant(restId);
    }

    /**
     *
     * @return Result contains the name of current user.
     */
    @GetMapping("/getUserName")
    public Result getUserName(){
        return restaurantManageService.getUserName();
    }


    /**Body Request
     * @param restaurantInfo e.g. {"restId":"1"}
     * @return Whether delete successfully
     */
    @PostMapping("/deleteRestaurant")
    public Result deleteRestaurant(@RequestBody Map<String,String> restaurantInfo){
        return restaurantManageService.restaurantDelete(restaurantInfo);
    }
}
