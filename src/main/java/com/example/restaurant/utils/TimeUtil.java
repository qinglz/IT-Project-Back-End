package com.example.restaurant.utils;

import java.time.LocalDateTime;

public class TimeUtil {
    public static String toSqlDateTime(LocalDateTime localDateTime){
        String s = localDateTime.toString();
        s = s.replace('T',' ');
        return s;
    }
}
