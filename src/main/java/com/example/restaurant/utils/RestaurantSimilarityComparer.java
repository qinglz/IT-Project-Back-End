package com.example.restaurant.utils;

import com.example.restaurant.pojo.Restaurant;

import java.util.Comparator;

public class RestaurantSimilarityComparer implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return Double.compare(r2.getSimilarity(),r1.getSimilarity());
    }
}
