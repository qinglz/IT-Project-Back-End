package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.Result;
import com.example.restaurant.entities.*;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.services.BookingService;
import com.example.restaurant.utils.EmailSender;
import com.example.restaurant.utils.SmsSender;
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
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*[.][a-z]*$";
    private final String phoneFormat = "^0\\d{9}$";
    private final String nameFormat = "^[A-Z a-z]+$";

    @Autowired
    EmailSender emailSender;
    @Autowired
    SearchingMapper searchingMapper;
    @Autowired
    BookingMapper bookingMapper;
    @Autowired
    SmsSender smsSender;
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
        String restIds = bookingInfo.get("restId");
        String numPeoples = bookingInfo.get("numPeople");
        String costumeName = bookingInfo.get("name");
        String phoneNumber = bookingInfo.get("phoneNumber");
        String email = bookingInfo.get("email");
        if(!costumeName.matches(nameFormat)){
            return Result.error("Incorrect name format.");
        }else if(!phoneNumber.matches(phoneFormat)){
            return Result.error("Incorrect phone number format.");
        }else if(email!=null&&!email.matches(emailFormat)){
            return Result.error("Incorrect email format.");
        }
        LocalDateTime dateTime = LocalDateTime.parse(bookingInfo.get("dateTime"));
        if(restIds==null||numPeoples==null
        ||costumeName==null||phoneNumber==null||dateTime==null){
            return Result.error("Missing required booking information!");
        }
        int restId = Integer.parseInt(restIds);
        int numPeople = Integer.parseInt(numPeoples);
        Boolean stillAvailable = availableAt(restId,numPeople,dateTime);
        if(!stillAvailable){
            return Result.error("Oops, the tables have just booked by others!");
        }
        if(!notOverBook(bookingInfo)){
            return Result.error("You have other conflicting reservations during this period!");
        }
        try {
        List<Table> tables = getAvailableTable(restId,dateTime);
        Collections.sort(tables,new TableCapacityComparator());
        List<Table> allocatedTables = TableAllocation.allocate(numPeople,tables);
        Restaurant restaurant = searchingMapper.findRestaurantById(restIds);
        String restName = restaurant.getName();
        //Maybe using BookingDto later
        List<BookingDto> bookingDtos = new ArrayList<>();
        for (Table table : allocatedTables){
            Booking booking = new Booking();
            booking.setCustomerPhoneNumber(phoneNumber);
            booking.setCustomerEmail(email);
            booking.setCustomerName(costumeName);
            booking.setDateTime(dateTime);
            booking.setTableId(String.valueOf(table.getId()));
            booking.setNumPeople(numPeople);
            bookingMapper.insert(booking);
            BookingDto bookingDto = new BookingDto(restName,costumeName,phoneNumber,email,dateTime,table.getTableNumber(),numPeople);
            bookingDtos.add(bookingDto);
        }
        String subject = "You got a new booking";
        String message = "Dear business owner of "+restName+" , you got some new bookings!\n"+ bookingDtos;
        String restOwnerEmail = searchingMapper.findBusinessUserById(String.valueOf(restaurant.getOwnerId())).getEmail();

        EmailDetails emailDetails = new EmailDetails(restOwnerEmail,message,subject);
        String smsMessage = "Dear customer, your booking has been confirmed. \n"+bookingDtos;
        SMSDetails smsDetails = new SMSDetails(phoneNumber,smsMessage);
        emailSender.sendEmail(emailDetails);
        smsSender.sendSMS(smsDetails);

        return Result.success(bookingDtos);
        }catch (Exception e){
            return Result.error("Fail to book the restaurant, please try again.");
        }







    }

    @Override
    public boolean notOverBook(Map<String, String> bookingInfo) {
        String phoneNumber = bookingInfo.get("phoneNumber");
        LocalDateTime dateTime = LocalDateTime.parse(bookingInfo.get("dateTime"));
        LocalDateTime from = dateTime.minus(timeSpan);
        LocalDateTime to = dateTime.plus(timeSpan);
        List<Booking> overlapBookings = bookingMapper.getBookingByUserAndTime(phoneNumber,from,to);
        return overlapBookings.isEmpty();
    }
}
