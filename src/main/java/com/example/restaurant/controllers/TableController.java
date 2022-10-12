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

    /**
     * e.g. "/getTables?id=1"
     * @param id  id of restaurant
     * @return
     */
    @GetMapping("/getTables")
    public Result getTables(@RequestParam("id") String id){
        return tableService.getTableByRestaurant(id);
    }


    /**
     * Body Request
     * @param info  e.g. [{"restId":"1","tableNumber":"1"}....]
     * @return Whether delete successfully
     */
    @PostMapping("/deleteTables")
    public Result deleteTables(@RequestBody List<Map<String,String>> info){
        return tableService.deleteTables(info);
    }


    /**
     * Body Request
     * @param info e.g [{"restId":"1","tableNumber":"2","capacity":"6"},{"restId":"1","tableNumber":"3","capacity":"6"}......]
     * @return Whether add successfully
     */
    @PostMapping("/addTables")
    public Result addTables(@RequestBody List<Map<String,String>> info){
        return tableService.addTables(info);
    }

}
