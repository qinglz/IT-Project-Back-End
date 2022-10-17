package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Table;
import com.example.restaurant.mapper.TableMapper;
import com.example.restaurant.services.TableService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    TableMapper tableMapper;


    @Override
    public Result getTableByRestaurant(String id) {
        List<Table> tables;
        try {
            tables = tableMapper.getTableByRestId(Integer.parseInt(id));

        } catch (Exception e) {
            return Result.error("Fail to get restaurant's table.");
        }
        return Result.success(tables);
    }

    @Override
    public Result deleteTables(List<Map<String, String>> info) {
        if (info.isEmpty()){
            return Result.success(null);
        }
        List<Integer> deletedNumber = new ArrayList<>();
        for (Map<String,String> k : info){
            deletedNumber.add(Integer.parseInt(k.get("dbid")));
        }
        try{
            tableMapper.deleteCertainTables(deletedNumber);

        }catch (Exception e){
            System.out.println(e);
            return Result.error("Fail to delete tables.");
        }
        return Result.success("Delete tables successfully");
    }

    @Override
    public Result addTables(List<Map<String, String>> info) {
        if (info.isEmpty()){
            return Result.success(null);
        }
        List<Table> tables = new ArrayList<>();
        for (Map<String,String> k : info){
            Table newTable = new Table(Integer.parseInt(k.get("restId")),Integer.parseInt(k.get("tableNumber")), Integer.parseInt(k.get("capacity")),Double.valueOf(k.get("xpos")),Double.valueOf(k.get("ypos")));
            newTable.setDeleted(0);
            tables.add(newTable);
        }
        try {
            for(Table table : tables){
                tableMapper.insert(table);

                substitution(table);


            }
        }catch (Exception e){
            System.out.println(e);
            return Result.error("Fail to add tables");
        }
        return Result.success("Add tables successfully");
    }


    public void substitution(Table table) {
        LocalDateTime now = LocalDateTime.now();
        tableMapper.substitute(String.valueOf(table.getId()),String.valueOf(table.getRestaurantId()),String.valueOf(table.getTableNumber()),now);

    }

    public Result updateTables(List<Map<String,String>> info){
        if (info.isEmpty()){
            return Result.success(null);
        }
        try {
            for (Map<String ,String>k : info){
                String id = k.get("dbid");
                String xpos = k.get("xpos");
                String ypos = k.get("ypos");
                tableMapper.updateATable(id,xpos,ypos);

            }
        }catch (Exception e){
            return Result.error("Update failed.");
        }
        return Result.success("Update successfully");






    }
}

