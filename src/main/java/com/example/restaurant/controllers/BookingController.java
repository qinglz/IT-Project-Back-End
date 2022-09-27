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

/**
 * This is controller for Api related to booking.
 */
@RestController
public class BookingController {
    @Autowired
    BookingService bookingService;


    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param info {"restId",value,"numPeople",value,"dateTime":"yyyy-MM-DDTHH:mm:ss"}
     *             yyyy is for year. MM for month. DD for dayInMonth. T is just a Letter for parsing,don't change it.
     *             HH for hour(in 24), mm for minutes, ss for second.
     * @return this method will return a result type that show whether there's table for a customer.
     */
    @GetMapping("/checkAvailability")
    public Result checkAvailability(@RequestBody Map<String,String> info){
        if (bookingService.availableAt(Integer.parseInt(info.get("restId")),Integer.parseInt(info.get("numPeople")),LocalDateTime.parse(info.get("dateTime")))){
            return Result.success("We got tables for you!");
        }else {
            return Result.error("There's no table available at that time.");
        }
    }

    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param bookingInfo {"restId",value,"numPeople",value,"dateTime":"yyyy-MM-DDTHH:mm:ss","name":value,"phoneNumber":value}("email",value)
     *                    attribute in {} is required in () is optional.
     *                    yyyy is for year. MM for month. DD for dayInMonth. T is just a letter T,don't change it(parsing format).
     *                    HH for hour(in 24), mm for minutes, ss for second.
     * @return
     */
    @PostMapping("/addBooking")
    public Result addBooking(@RequestBody Map<String,String> bookingInfo){
        return bookingService.addBooking(bookingInfo);
    }
    //to do: add a new booking
}
