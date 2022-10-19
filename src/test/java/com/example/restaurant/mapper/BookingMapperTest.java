package com.example.restaurant.mapper;

import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Table;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {
    @Autowired
    BookingMapper bookingMapper;
    private final LocalDateTime t = LocalDateTime.of(2022,9,22,14,30);
    @Test
    void getAvailableTable() {
        List<Table> tables = bookingMapper.getAvailableTable(1,t,t.plusHours(1L));
        System.out.println(tables);
    }

    @Test
    void getAvailableCapacity() {

        int capacity = bookingMapper.getAvailableCapacity(1, t,t.plusHours(1L));
        System.out.println(capacity);
    }

    @Test
    void getBookingsByRestId() {
        List<BookingDto> bookings = bookingMapper.getBookingsByRestId(1);
        System.out.println(bookings);

    }

    @Test
    void getUsedCapacity() {
        int capacity = bookingMapper.getUsedCapacity(1, t,t.plusHours(1L));
        System.out.println(capacity);
    }

    @Test
    void getBookingByUserAndTime() {

    }
}