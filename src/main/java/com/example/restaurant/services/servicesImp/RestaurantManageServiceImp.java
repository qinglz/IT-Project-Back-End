package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Dto.BookingDto;
import com.example.restaurant.Result;
import com.example.restaurant.entities.Booking;
import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.entities.LoginUser;
import com.example.restaurant.mapper.BookingMapper;
import com.example.restaurant.mapper.RestaurantMapper;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.BookingService;
import com.example.restaurant.services.RestaurantManageService;
import com.example.restaurant.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RestaurantManageServiceImp implements RestaurantManageService {
    @Autowired
    RestaurantMapper restaurantMapper;
    @Autowired
    BookingMapper bookingMapper;

    @Autowired
    EmailSender emailSender;

    public Result restaurantRegister(Map<String,String> restaurantInfo){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        BusinessUser businessUser = ((LoginUser) authentication.getPrincipal()).getBusinessUser();
        String restName = restaurantInfo.get("restName");

        try {
            int businessId = businessUser.getId();
            String address = restaurantInfo.get("address");
            int capacity = Integer.parseInt(restaurantInfo.get("capacity"));
            int numStaff = Integer.parseInt(restaurantInfo.get("numStaff"));
            Restaurant restaurant = new Restaurant(restName, address, businessId, capacity, numStaff);
            restaurant.setDeleted(0);
//            if (searchingMapper.checkRestaurantByAddress(address) != null) {
//                return Result.error("");
//            }
            restaurantMapper.insert(restaurant);
        }catch (Exception e) {
            return Result.error("Fail to add new restaurant.");

        }
//        Not use email for now.
//        try {
//            String to = businessUser.getEmail();
//            String subject = "Create Restaurant Successfully!";
//            String message = "You have successfully created a restaurant: " + restName + " !";
//            EmailDetails emailDetails = new EmailDetails(to, subject, message);
//            emailSender.sendEmail(emailDetails);
//
//        }catch (Exception e){
//            return Result.partialError(null,"Create restaurant successfully, but fail to send email.");
//        }
        return Result.success("New restaurant created.");

    }
    @Override
    public Result restaurantUpdate(Map<String,String> restaurantInfo){
        try {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            BusinessUser businessUser = ((LoginUser) authentication.getPrincipal()).getBusinessUser();
            int businessId = businessUser.getId();
            String restID = restaurantInfo.get("restId");
            String restName = restaurantInfo.get("restName");
            String address = restaurantInfo.get("address");
            int capacity = Integer.parseInt(restaurantInfo.get("capacity"));
            int numStaff = Integer.parseInt(restaurantInfo.get("numStaff"));
            Restaurant restaurant = new Restaurant(restID,restName, address, businessId, capacity, numStaff);
//            if (searchingMapper.checkRestaurantByAddress(address) != null) {
//                return Result.error("");
//            }
            restaurantMapper.updateById(restaurant);
            return Result.success("Restaurant updated.");
        }catch (Exception e){
            return Result.error("Fail to update restaurant.");
        }

    }

    @Override
    public Result getAllRestaurants() {
        try {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            BusinessUser businessUser = ((LoginUser) authentication.getPrincipal()).getBusinessUser();
            List<Restaurant> restaurants = restaurantMapper.findRestaurantByOwnerId(String.valueOf(businessUser.getId()));
            return Result.success(restaurants);
        }catch (Exception e){
            return Result.error("Fail to load restaurant information.");
        }



    }
    @Override
    public Result getUserName(){
        try {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            BusinessUser businessUser = ((LoginUser) authentication.getPrincipal()).getBusinessUser();
            String name = businessUser.getName();
            return Result.success(name);
        }catch (Exception e){
            return Result.error("Fail to load name information.");
        }
    }
    @Override
    public Result restaurantDelete(Map<String,String> restaurantInfo){
        String restId = restaurantInfo.get("restId");
        try {
            restaurantMapper.deleteARestaurant(restId);
            restaurantMapper.deleteTablesByRestaurant(restId);
        }catch (Exception e){
            return Result.error("Fail to delete restaurant.");
        }

        return Result.success("Delete restaurant Successfully.");

    }

    @Override
    public Result getBookingByRestaurant(String id) {
        try {
            List<BookingDto> bookingDtos = bookingMapper.getBookingsByRestId(Integer.parseInt(id));
            return Result.success(bookingDtos);
        }catch (Exception e){
            return Result.error("Failed to get bookings.");
        }

    }
}
