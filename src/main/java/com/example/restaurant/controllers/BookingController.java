package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import com.example.restaurant.services.BookingService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @GetMapping("/checkAvailability")
    public Result checkAvailability(@RequestBody Map<String,String> info){
        if (bookingService.availableAt(Integer.parseInt(info.get("restId")),Integer.parseInt(info.get("numPeople")),LocalDateTime.parse(info.get("dateTime")))){
            return Result.success("We got tables for you!");
        }else {
            return Result.error("There's no table available at that time.");
        }
    }
    @PostMapping("/addBooking")
    public Result addBooking(@RequestBody Map<String,String> bookingInfo){
        return bookingService.addBooking(bookingInfo);
    }
    //to do: add a new booking
}
