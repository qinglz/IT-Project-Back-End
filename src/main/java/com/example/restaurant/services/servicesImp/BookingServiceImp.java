package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.Result;
import com.example.restaurant.entities.*;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.RestaurantMapper;
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
    private final Duration timeSpan = Duration.ofMinutes(119L);
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*[.]*[a-z]*$";
    private final String phoneFormat = "^0\\d{9}$";
    private final String timeFormat = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$";
    private final String nameFormat = "^[A-Z a-z]+$";

    @Autowired
    EmailSender emailSender;
    @Autowired
    RestaurantMapper restaurantMapper;
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
        Restaurant restaurant = restaurantMapper.findRestaurantById(String.valueOf(restId));
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
        String costumerName = bookingInfo.get("name");
        String phoneNumber = bookingInfo.get("phoneNumber");
        String email = bookingInfo.get("email");
        String dateTimes = bookingInfo.get("dateTime");
        if (restIds == null || numPeoples == null ||email ==null
                || costumerName == null || phoneNumber == null || dateTimes == null) {
            return Result.error("Missing required booking information!");
        }
        if (!costumerName.matches(nameFormat)) {
            return Result.error("Incorrect name format.");
        } else if (!phoneNumber.matches(phoneFormat)) {
            return Result.error("Incorrect phone number format.");
        } else if (email != null && !email.matches(emailFormat)) {
            return Result.error("Incorrect email format.");
        } else if (!dateTimes.matches(timeFormat)){
            return Result.error("Incorrect time format");
        }

        LocalDateTime dateTime = LocalDateTime.parse(dateTimes);
        int restId = Integer.parseInt(restIds);
            int numPeople = Integer.parseInt(numPeoples);
        Boolean stillAvailable = availableAt(restId, numPeople, dateTime);
        if (!stillAvailable) {
            return Result.error("Oops, the tables have just booked by others!");
        }
        if (!notOverBook(bookingInfo)) {
            return Result.error("You have other conflicting reservations during this period!");
        }
        String restName;
        List<BookingDto> bookingDtos;
        Restaurant restaurant;
        try {
            List<Table> tables = getAvailableTable(restId, dateTime);
            tables.sort(new TableCapacityComparator());
            List<Table> allocatedTables = TableAllocation.allocate(numPeople, tables);
            restaurant = restaurantMapper.findRestaurantById(restIds);
            restName = restaurant.getName();
            //Maybe using BookingDto later
            bookingDtos = new ArrayList<>();
            for (Table table : allocatedTables) {
                Booking booking = new Booking();
                booking.setCustomerPhoneNumber(phoneNumber);
                booking.setCustomerEmail(email);
                booking.setCustomerName(costumerName);
                booking.setDateTime(dateTime);
                booking.setTableId(String.valueOf(table.getId()));
                booking.setTableNum(table.getTableNumber());
                booking.setNumPeople(numPeople);
                booking.setRestId(restId);
                bookingMapper.insert(booking);
                BookingDto bookingDto = new BookingDto(restName, costumerName, phoneNumber, email, dateTime, table.getTableNumber(), numPeople,booking.getId());
                bookingDtos.add(bookingDto);
            }
        } catch (Exception e) {
            return Result.error("Fail to book the restaurant, please try again.");
        }


        String subject = "You got a new booking";
        String businessUserMessage = "Dear business owner of " + restName + ", you got some new bookings!\n" + bookingDtos+"\nThanks for using RestBook";
        String restOwnerEmail = restaurantMapper.findBusinessUserById(String.valueOf(restaurant.getOwnerId())).getEmail();

        EmailDetails businessOwnerEmailDetails = new EmailDetails(restOwnerEmail, businessUserMessage, subject);
        String customerMessage = "Dear customer, your booking has been confirmed. \n" + bookingDtos + "\nThanks for using RestBook";
        EmailDetails customerEmailDetails = new EmailDetails(email,customerMessage,subject);
//        SMSDetails smsDetails = new SMSDetails("+61" + phoneNumber, smsMessage);
        try{
            emailSender.sendEmail(businessOwnerEmailDetails);
        }catch (Exception e){
            try {
                emailSender.sendEmail(customerEmailDetails);
            }catch (Exception exception){
                return Result.partialError(bookingDtos,"Add booking successfully but fail to send email to both business owner and customer");
            }
            return Result.partialError(bookingDtos, "Add booking successfully but fail to send email to business owner.");
        }

        try{
            emailSender.sendEmail(customerEmailDetails);
        }catch (Exception e){
            return Result.partialError(bookingDtos, "Add booking successfully but fail to send email to customer.");
        }
//        try {
//            smsSender.sendSMS(smsDetails);
//        } catch (Exception e) {
//            return Result.partialError(bookingDtos, "Add booking successfully but fail to send SMS");
//        }
        return Result.success(bookingDtos);


    }

    @Override
    public boolean notOverBook(Map<String, String> bookingInfo) {
        String phoneNumber = bookingInfo.get("phoneNumber");
        String email = bookingInfo.get("email");
        LocalDateTime dateTime = LocalDateTime.parse(bookingInfo.get("dateTime"));
        LocalDateTime from = dateTime.minus(timeSpan);
        LocalDateTime to = dateTime.plus(timeSpan);
        List<Booking> overlapBookings1 = bookingMapper.getBookingByUserPhoneAndTime(phoneNumber,from,to);
        List<Booking> overlapBookings2 = bookingMapper.getBookingByUserEmailAndTime(email,from,to);
        return overlapBookings1.isEmpty()&&overlapBookings2.isEmpty();
    }
}
