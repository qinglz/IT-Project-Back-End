package com.example.restaurant.services;

import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.entities.Table;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookingService {
    public List<Table> getAvailableTable(int restId, int numPeople, LocalDateTime datetime);
    public boolean availableAt(int restId, int numPeople, LocalDateTime datetime);
}
