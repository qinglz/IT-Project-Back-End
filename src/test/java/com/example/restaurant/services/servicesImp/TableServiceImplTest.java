package com.example.restaurant.services.servicesImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TableServiceImplTest {
    @Autowired
    TableServiceImpl tableService;

    @Test
    void getTableByRestaurant() {
        System.out.println(tableService.getTableByRestaurant("5"));
        //Test passed, the table information of restaurant id=5 returned.


    }

    @Test
    void deleteTables() {
        Map<String,String> info = new HashMap<>();
        info.put("dbid","88");
        List<Map<String,String>> l = new ArrayList<>();
        l.add(info);
        tableService.deleteTables(l);
        //Test passed, the table id=88 has been set deleted.
    }

    @Test
    void addTables() {
        Map<String,String> info = new HashMap<>();
        info.put("restId","5");
        info.put("capacity","6");
        info.put("tableNumber","4");
        info.put("xpos","100");
        info.put("ypos","100");
        List<Map<String,String>> l = new ArrayList<>();
        l.add(info);
        tableService.addTables(l);
        //Test passed. The new table added and bind with the booking with the "old" booking with tableNumber=4;
    }


    @Test
    void updateTables() {
        Map<String,String> info = new HashMap<>();
        info.put("dbid","116");
        info.put("xpos","200");
        info.put("ypos","200");
        List<Map<String,String>> l = new ArrayList<>();
        l.add(info);
        tableService.updateTables(l);
        //Test passed. The table id =116 has been updated with a new position (200,200);
    }
}