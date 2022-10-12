package com.example.restaurant.services;

import com.example.restaurant.Result;

import java.util.List;
import java.util.Map;

public interface TableService {
    public Result getTableByRestaurant(String id);
    public Result deleteTables(List<Map<String, String>> info);
    public Result addTables(List<Map<String,String>> info);
}
