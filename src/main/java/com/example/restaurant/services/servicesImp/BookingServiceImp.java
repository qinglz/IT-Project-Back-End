package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.entities.Table;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.services.BookingService;
import com.example.restaurant.utils.TableAllocation;
import com.example.restaurant.utils.TableCapacityComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingServiceImp implements BookingService {
    private final Duration timeSpan = Duration.ofMinutes(59L);
    @Autowired
    SearchingMapper searchingMapper;
    @Autowired
    BookingMapper bookingMapper;
    @Override
    public List<Table> getAvailableTable(int restId,LocalDateTime dateTime) {
        LocalDateTime from = dateTime.minus(timeSpan);
        LocalDateTime to = dateTime.plus(timeSpan);
        List<Table> tables = bookingMapper.getAvailableTable(restId, from, to);
        return tables;

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
            Integer usedCapacity1 = bookingMapper.getUsedCapacity(restId,from,dateTime);
            Integer usedCapacity2 = bookingMapper.getUsedCapacity(restId,dateTime,to);
            Integer usedCapacity = null;
            if(usedCapacity1==null&&usedCapacity2!=null){usedCapacity = usedCapacity2;}
            else if(usedCapacity2==null&&usedCapacity1!=null){usedCapacity = usedCapacity1;}
            else if(usedCapacity2!=null&&usedCapacity1!=null){usedCapacity = Integer.max(usedCapacity1,usedCapacity2);}
            if(usedCapacity!=null&&usedCapacity+numPeople>restaurantCapacity){
                return false;
            }
            return true;
        }



    }

    @Override
    public Result addBooking(Map<String,String> bookingInfo) {
        int restId = Integer.parseInt(bookingInfo.get("restId"));
        int numPeople = Integer.parseInt(bookingInfo.get("numPeople"));
        String costumeName = bookingInfo.get("name");
        String phoneNumber = bookingInfo.get("phoneNumber");
        String email = bookingInfo.get("email");
        LocalDateTime dateTime = LocalDateTime.parse(bookingInfo.get("dateTime"));
        Boolean stillAvailable = availableAt(restId,numPeople,dateTime);
        if(!stillAvailable){
            return Result.error("Oops, the tables have just booked by others");
        }
        List<Table> tables = getAvailableTable(restId,dateTime);
        Collections.sort(tables,new TableCapacityComparator());
        List<Table> allocatedTables = TableAllocation.allocate(numPeople,tables);
        //Maybe using BookingDto later
        List<Booking> bookings = new ArrayList<>();
        for (Table table : allocatedTables){
            Booking booking = new Booking();
            booking.setCustomerPhoneNumber(phoneNumber);
            booking.setCustomerEmail(email);
            booking.setCustomerName(costumeName);
            booking.setDateTime(dateTime);
            booking.setTableId(String.valueOf(table.getId()));
            booking.setNumPeople(numPeople);
            bookingMapper.insert(booking);
            bookings.add(booking);
        }
        return Result.success(bookings);

    }
}
