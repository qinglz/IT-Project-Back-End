package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.RestaurantManageService;
import com.example.restaurant.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantManageServiceImp implements RestaurantManageService {
    @Autowired
    SearchingMapper searchingMapper;

    @Autowired
    EmailSender emailSender;
    @Autowired
    EmailDetails emailDetails;

    public Result restaurantRegister(Restaurant restaurant){

        if (searchingMapper.checkRestaurantByName(restaurant.getName())!= null){
           return Result.error("The restaurant name is existed");
        }else{
            searchingMapper.insert(restaurant);
            emailSender.sendEmail(emailDetails);
            return Result.success(restaurant);
        }
    }
}
