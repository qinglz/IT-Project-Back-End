package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import com.example.restaurant.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;
    @GetMapping("/checkAvailability")
    public Result checkAvailability(int restId, int numPeople, LocalDateTime dateTime){
        if (bookingService.availableAt(restId,numPeople,dateTime)){
            return Result.success("There's table for you!");
        }else {
            return Result.error("There's no table available at that time.");
        }
    }
}
