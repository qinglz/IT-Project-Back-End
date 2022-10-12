package com.example.restaurant.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RestaurantMapperTest {
    @Autowired
    RestaurantMapper restaurantMapper;


    @Test
    void searchRestaurantByName() {
        System.out.println(restaurantMapper.searchRestaurantByName("Chicken"));

    }

    @Test
    void checkRestaurantByName() {
//        System.out.println(searchingMapper.checkRestaurantByName("Chicken Wings Expert"));
    }

    @Test
    void findRestaurantById() {
        System.out.println(restaurantMapper.findRestaurantById("1"));
    }
}