package com.example.restaurant.untils;

import com.example.restaurant.pojo.Restaurant;
import info.debatty.java.stringsimilarity.Cosine;

import java.util.Comparator;

public class RestaurantSimilarityComparer implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return Double.compare(r2.getSimilarity(),r1.getSimilarity());
    }
}
