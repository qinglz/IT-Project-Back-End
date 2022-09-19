package com.example.restaurant.services.servicesImp;

import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.mapper.SearchingMapper;
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
    SearchingMapper searchingMapper;
    @Override
    public List<Restaurant> getAllRestaurant() {
        return searchingMapper.selectList(null);
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
        List<Restaurant> restaurants = searchingMapper.searchRestaurantByName(regex);
        Cosine cosine = new Cosine();
        for(Restaurant r :restaurants){
            r.setSimilarity(cosine.similarity(r.getName(),name));
        }
        Collections.sort(restaurants, new RestaurantSimilarityComparer());
        return restaurants;
    }
}
