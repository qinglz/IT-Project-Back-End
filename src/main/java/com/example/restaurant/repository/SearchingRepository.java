package com.example.restaurant.repository;

import com.example.restaurant.pojo.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SearchingRepository extends MongoRepository<Restaurant,String> {
    public List<Restaurant> findAllByNameContains(List<String> s);

}
