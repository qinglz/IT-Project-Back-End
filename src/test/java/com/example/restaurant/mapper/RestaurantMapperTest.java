package com.example.restaurant.mapper;

import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.entities.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RestaurantMapperTest {
    @Autowired
    RestaurantMapper restaurantMapper;
    @Test
    void searchRestaurantByName() {
        List<Restaurant> restaurants =restaurantMapper.searchRestaurantByName("chicken");
        System.out.println(restaurants);
        //Test passed all restaurant returned with name containing "chicken".
    }

//    @Test
//    void checkRestaurantByAddress() {
//    }

    @Test
    void findRestaurantById() {
        Restaurant restaurant = restaurantMapper.findRestaurantById("5");
        System.out.println(restaurant);
        //Test passed, correct restaurant was returned.
    }

    @Test
    void findBusinessUserById() {
        BusinessUser businessUser = restaurantMapper.findBusinessUserById("89");
        System.out.println(businessUser);
        //Test passed, correct user was returned.

    }

    @Test
    void findRestaurantByOwnerId() {
        List<Restaurant> restaurants = restaurantMapper.findRestaurantByOwnerId("89");
        System.out.println(restaurants);
        //Test passed, all restaurants owned by this user has been returned.
    }

    @Test
    void deleteARestaurant() {
        restaurantMapper.deleteARestaurant("9");
        //Test passed, the restaurant with id =9 has been set deleted.

    }

    @Test
    void deleteTablesByRestaurant() {
        restaurantMapper.deleteTablesByRestaurant("12");
        //Test passed, the tables belonging to this restaurant has been set deleted.
    }
}