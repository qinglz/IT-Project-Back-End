package com.example.restaurant.services.servicesImp;

import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.repository.SearchingRepository;
import com.example.restaurant.services.SearchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<String> s = List.of(name.split(" "));
        return searchingRepository.findAllByNameContains(s);
    }
}
