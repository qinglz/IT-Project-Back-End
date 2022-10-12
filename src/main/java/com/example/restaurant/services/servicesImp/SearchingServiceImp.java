package com.example.restaurant.services.servicesImp;

import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.mapper.RestaurantMapper;
import com.example.restaurant.services.SearchingService;
import com.example.restaurant.utils.RestaurantSimilarityComparer;
import info.debatty.java.stringsimilarity.Cosine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SearchingServiceImp implements SearchingService {

    @Autowired
    RestaurantMapper restaurantMapper;
    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantMapper.selectList(null);
    }

    @Override
    public List<Restaurant> findRestaurantByName(String name) {
        List<String> parts = List.of(name.split(" "));
        String regex = ".*(";
        for(String part : parts){
            regex +=part+"|";
        }
        regex = regex.substring(0,regex.length()-1);
        regex+=").*";
        List<Restaurant> restaurants = restaurantMapper.searchRestaurantByName(regex);
        Cosine cosine = new Cosine();
        for(Restaurant r :restaurants){
            r.setSimilarity(cosine.similarity(r.getName(),name));
        }
        Collections.sort(restaurants, new RestaurantSimilarityComparer());
        return restaurants;
    }
}
