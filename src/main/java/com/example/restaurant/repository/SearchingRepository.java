package com.example.restaurant.repository;

import com.example.restaurant.pojo.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchingRepository extends MongoRepository<Restaurant,String> {
    @Query("{name:{$regex:?0}}")
    public List<Restaurant> findAllByNameLike(String nameRegex);

}
