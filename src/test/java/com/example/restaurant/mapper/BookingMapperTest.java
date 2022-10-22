package com.example.restaurant.mapper;

import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import com.example.restaurant.services.BookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookingMapperTest {
    @Autowired
    BookingMapper bookingMapper;
    @Autowired
    BookingService bookingService;
    private final int testedRestId = 5;
    private final LocalDateTime t = LocalDateTime.of(2022,11,22,14,30);
    private final String email = "zql20000924@163.com";
    private final String phone = "0432477804";
    @Test
    void getAvailableTable() {
        List<Table> tables = bookingMapper.getAvailableTable(testedRestId,t,t.plusHours(1L));
        System.out.println(tables);
        //Test passed, return list not contain the table has been booked at that time.
    }

    @Test
    void getAvailableCapacity() {

        int capacity = bookingMapper.getAvailableCapacity(testedRestId, t,t.plusHours(1L));
        System.out.println(capacity);
        //Test passed, the calculation of capacity is correct.
    }

    @Test
    void getBookingsByRestId() {
        List<BookingDto> bookings = bookingMapper.getBookingsByRestId(testedRestId);
        System.out.println(bookings);
        //Test passed, correct booking returned.

    }

    @Test
    void getUsedCapacity() {
        int capacity = bookingMapper.getUsedCapacity(testedRestId, t,t.plusHours(1L));
        System.out.println(capacity);
        //Test passed, returned used capacity was correct.
    }

    @Test
    void getBookingByUserPhoneAndTime() {
        List<Booking> bookings = bookingMapper.getBookingByUserPhoneAndTime(phone,t,t.plusHours(1L));
        System.out.println(bookings);
        //Test passed, successfully catch the booking for a certain customer by searching phone number.

    }

    @Test
    void getBookingByUserEmailAndTime() {
        List<Booking> bookings = bookingMapper.getBookingByUserEmailAndTime(email,t,t.plusHours(1L));
        System.out.println(bookings);
        //Test passed, successfully catch the booking for a certain customer by searching email address.
    }
    @Test
    void addBooking(){
        Map<String,String> info = new HashMap<>();
        info.put("restId",String.valueOf(testedRestId));
        info.put("numPeople","5");
        info.put("phoneNumber",phone);
        info.put("email",email);
        info.put("name","QL");
        info.put("dateTime","2022-11-22T14:30:00");
        System.out.println(bookingService.addBooking(info));
        //Just input a booking for testing.

    }
}