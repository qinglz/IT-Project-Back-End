package com.example.restaurant.controllers;


import com.example.restaurant.Result;
import com.example.restaurant.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TableController {
    @Autowired
    TableService tableService;

    @GetMapping("/getTables")
    public Result getTables(@RequestParam("id") String id){
        return tableService.getTableByRestaurant(id);
    }
    @PostMapping("/deleteTables")
    public Result deleteTables(@RequestBody List<Map<String,String>> info){
        return tableService.deleteTables(info);
    }
    @PostMapping("/addTables")
    public Result addTables(@RequestBody List<Map<String,String>> info){
        return tableService.addTables(info);
    }

}
