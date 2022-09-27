package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;
import com.example.restaurant.mapper.SearchingMapper;
import com.example.restaurant.entities.Restaurant;
import com.example.restaurant.services.EmailService;
import com.example.restaurant.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImp implements RegistrationService {
    @Autowired
    SearchingMapper searchingMapper;

    @Autowired
    EmailService senderService;
    @Autowired
    EmailDetails emailDetails;

    public Result restaurantRegister(Restaurant restaurant){

        if (searchingMapper.checkRestaurantByName(restaurant.getName())!= null){
           return Result.error("The restaurant name is existed");
        }else{
            searchingMapper.insert(restaurant);
            senderService.sendEmail(emailDetails);
            return Result.success(restaurant);
        }
    }
}
