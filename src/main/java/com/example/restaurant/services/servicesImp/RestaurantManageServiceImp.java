package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.entities.EmailDetails;
import com.example.restaurant.entities.LoginUser;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RestaurantManageService;
import com.example.restaurant.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RestaurantManageServiceImp implements RestaurantManageService {
    @Autowired
    SearchingMapper searchingMapper;

    @Autowired
    EmailSender emailSender;

    public Result restaurantRegister(Map<String,String> restaurantInfo){
        try {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            BusinessUser businessUser = ((LoginUser) authentication.getPrincipal()).getBusinessUser();
            int businessId = businessUser.getId();
            String restName = restaurantInfo.get("restName");
            String address = restaurantInfo.get("address");
            int capacity = Integer.parseInt(restaurantInfo.get("capacity"));
            int numStaff = Integer.parseInt(restaurantInfo.get("numStaff"));
            Restaurant restaurant = new Restaurant(restName, address, businessId, capacity, numStaff);
            if (searchingMapper.checkRestaurantByName(restName) != null) {
                return Result.error("The restaurant name is existed");
            }
            searchingMapper.insert(restaurant);
            String to = businessUser.getEmail();
            String subject = "Create Restaurant Successfully!";
            String message = "You have successfully created a restaurant: " + restName + " !";
            EmailDetails emailDetails = new EmailDetails(to, subject, message);
            emailSender.sendEmail(emailDetails);
            return Result.success("New restaurant created.");
        }catch (Exception e){
            return Result.error("Fail to create restaurant.");
        }

//        if (searchingMapper.checkRestaurantByName(restaurant.getName())!= null){
//           return Result.error("The restaurant name is existed");
//        }else{
//            searchingMapper.insert(restaurant);
//            emailSender.sendEmail(emailDetails);
//            return Result.success(restaurant);
//        }
    }
}
