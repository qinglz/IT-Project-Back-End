package com.example.restaurant.repository;

import com.example.restaurant.pojo.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationRepository extends MongoRepository<Restaurant, String> {
    Restaurant findRestaurantByName(String name);
}
