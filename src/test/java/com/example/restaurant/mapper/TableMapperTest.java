package com.example.restaurant.mapper;

import com.example.restaurant.entities.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TableMapperTest {
    @Autowired
    TableMapper tableMapper;
    @Test
    void getTableByRestId() {
        List<Table> tables = tableMapper.getTableByRestId(5);
        System.out.println(tables);
        System.out.println(tables.size());
        //Test passed, the number of tables and tables details are correct. The deleted tables have not been returned.
    }

    @Test
    void deleteCertainTables() {
        List<Integer> dNumber = new ArrayList<>();
        dNumber.add(98);
        dNumber.add(99);

        tableMapper.deleteCertainTables(dNumber);
        //Test passed, the tables with id=98and99 has been set deleted.
    }

    @Test
    void updateATable() {

        tableMapper.updateATable("200","100","96");
        //Test passed, the position of table id=96 has been set to (200,100);
    }
}