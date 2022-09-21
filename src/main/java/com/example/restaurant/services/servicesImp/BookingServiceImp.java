package com.example.restaurant.services.servicesImp;

import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.entities.Table;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Service
public class BookingServiceImp implements BookingService {
    private final Duration timeSpan = Duration.ofMinutes(59L);
    @Autowired
    SearchingMapper searchingMapper;
    @Autowired
    BookingMapper bookingMapper;
    @Override
    public List<Table> getAvailableTable(int restId, int numPeople,LocalDateTime datetime) {
        return null;
    }

    @Override
    public boolean availableAt(int restId, int numPeople, LocalDateTime dateTime) {
        Restaurant restaurant = searchingMapper.findRestaurantById(String.valueOf(restId));
        int restaurantCapacity = restaurant.getCapacity();
        LocalDateTime from = dateTime.minus(timeSpan);
        LocalDateTime to = dateTime.plus(timeSpan);
        Integer availableCapacity = bookingMapper.getAvailableCapacity(restId, from,to);
        if(availableCapacity==null||availableCapacity<numPeople){
            return false;
        }else{
            Integer usedCapacity = bookingMapper.getUsedCapacity(restId,from,to);
            if(usedCapacity!=null&&usedCapacity+numPeople>restaurantCapacity){
                return false;
            }
            return true;
        }



    }
}
