package com.example.restaurant.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SearchingMapperTest {
    @Autowired
    SearchingMapper searchingMapper;


    @Test
    void searchRestaurantByName() {
        System.out.println(searchingMapper.searchRestaurantByName("Chicken"));

    }

    @Test
    void checkRestaurantByName() {
        System.out.println(searchingMapper.checkRestaurantByName("Chicken Wings Expert"));
    }

    @Test
    void findRestaurantById() {
        System.out.println(searchingMapper.findRestaurantById("1"));
    }
}