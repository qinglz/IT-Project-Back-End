package com.example.restaurant.services.servicesImp;

import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.repository.SearchingRepository;
import com.example.restaurant.services.SearchingService;
import com.example.restaurant.untils.RestaurantSimilarityComparer;
import info.debatty.java.stringsimilarity.Cosine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SearchingServiceImp implements SearchingService {

    @Autowired
    SearchingRepository searchingRepository;
    @Override
    public List<Restaurant> getAllRestaurant() {
        return searchingRepository.findAll();
    }

    @Override
    public List<Restaurant> findRestaurantByName(String name) {
        List<String> parts = List.of(name.split(" "));
        String regex = "((?i)";
        for(String part : parts){
            regex +=part+"|";
        }
        regex = regex.substring(0,regex.length()-1);
        regex+=")";
        List<Restaurant> restaurants = searchingRepository.findAllByNameLike(regex);
        Cosine cosine = new Cosine();
        for(Restaurant r :restaurants){
            r.setSimilarity(cosine.similarity(r.getName(),name));
        }
        Collections.sort(restaurants, new RestaurantSimilarityComparer());
        return restaurants;
    }
}
