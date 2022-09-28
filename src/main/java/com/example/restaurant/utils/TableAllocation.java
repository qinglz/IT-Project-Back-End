package com.example.restaurant.utils;

import com.example.restaurant.entities.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableAllocation {
    public static List<Table> allocate(int numPeople, List<Table> sortedTables){
        if (sortedTables.get(0).getCapacity()>=numPeople){
            return List.of(allocateOne(numPeople,sortedTables));
        }
        List<Table> remains = sortedTables.subList(1,sortedTables.size());
        List<Table> tables = new ArrayList<>(List.of(sortedTables.get(0)));
        int remainPeople = numPeople-sortedTables.get(0).getCapacity();
        tables.addAll(allocate(remainPeople,remains));
        return tables;



    }
    private static Table allocateOne(int numPeople, List<Table> sortedTales){
        Table best = null;
        for (Table t : sortedTales){
            if(t.getCapacity()-numPeople>=0){
                best= t;
            }else {break;}
        }
        return best;
    }
}
