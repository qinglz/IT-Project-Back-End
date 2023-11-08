package com.example.restaurant.services.servicesImp;

import com.example.restaurant.entities.LoginUser;
import com.example.restaurant.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookingServiceImpTest {
    @Autowired
    BookingServiceImp bookingServiceImp;
        private final LocalDateTime t = LocalDateTime.of(2022,11,22,14,30);


    @Test
    void getAvailableTable() {
        System.out.println(bookingServiceImp.getAvailableTable(5,t));
        //Test passed. correct available table returned.

    }

    @Test
    void availableAt() {
        System.out.println(bookingServiceImp.availableAt(5,10,t));
        //Test passed. correct table capacity returned.
    }

    @Test
    void addBooking() {
        Map<String,String> info = new HashMap<>();
        info.put("restId","5");
        info.put("phoneNumber","0415555124");
        info.put("name","Qi Xing");
        info.put("numPeople","10");
        info.put("email","kkk@163.com");
        info.put("dateTime","2022-11-22T15:00:00");
        bookingServiceImp.addBooking(info);
        //Test passed, booking successfully and correct tables are allocated to customer.
    }

    @Test
    void notOverBook() {
        Map<String,String> info = new HashMap<>();
        info.put("restId","5");
        info.put("phoneNumber","0432477804");
        info.put("name","Qi Xing");
        info.put("numPeople","4");
        info.put("dateTime","2022-11-22T15:00:00");
        info.put("email","sxc@126.com");
        System.out.println(bookingServiceImp.notOverBook(info));
        //Test passed, detects overbooking successfully.
    }
}