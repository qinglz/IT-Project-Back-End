package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.repository.RegistrationRepository;
import com.example.restaurant.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImp implements RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;

    public Result restaurantRegister(Restaurant restaurant){

        if (registrationRepository.findRestaurantByName(restaurant.getName())!= null){
           return Result.error("The restaurant name is existed");
        }else{
            registrationRepository.insert(restaurant);
            return Result.success(restaurant);
        }
    }
}
