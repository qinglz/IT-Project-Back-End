package com.example.restaurant.services.servicesImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SearchingServiceImpTest {
    @Autowired
    SearchingServiceImp searchingServiceImp;
    @Test
    void findRestaurantByName() {
        System.out.println(searchingServiceImp.findRestaurantByName("Can I get some Chicken"));
        //Test passed. The key word "chicken" show all restaurant with name containing chicken.

    }
}