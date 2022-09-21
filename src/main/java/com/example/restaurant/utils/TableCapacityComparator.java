package com.example.restaurant.utils;

import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.entities.Table;

import java.util.Comparator;

public class TableCapacityComparator implements Comparator<Table> {
    @Override
    public int compare(Table t1, Table t2) {
        return Double.compare(t2.getCapacity(),t1.getCapacity());
    }
}
