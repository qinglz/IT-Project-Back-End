package com.example.restaurant.services.servicesImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceImpTest {
    @Autowired
    BookingServiceImp bookingServiceImp;
    private final LocalDateTime t = LocalDateTime.of(2022,9,22,14,30);


    @Test
    void getAvailableTable() {
        System.out.println(bookingServiceImp.getAvailableTable(1,t));

    }

    @Test
    void availableAt() {
        System.out.println(bookingServiceImp.availableAt(1,4,t));
    }

    @Test
    void addBooking() {
        Map<String,String> info = new HashMap<>();
        info.put("restId","1");
        info.put("phoneNumber","0415555124");
        info.put("name","Qi Xing");
        info.put("numPeople","4");
        info.put("dateTime","2022-9-22T15:00:00");
        bookingServiceImp.addBooking(info);
    }

    @Test
    void notOverBook() {
        Map<String,String> info = new HashMap<>();
        info.put("restId","1");
        info.put("phoneNumber","0415555124");
        info.put("name","Qi Xing");
        info.put("numPeople","4");
        info.put("dateTime","2022-9-22T15:00:00");
        System.out.println(bookingServiceImp.notOverBook(info));
    }
}