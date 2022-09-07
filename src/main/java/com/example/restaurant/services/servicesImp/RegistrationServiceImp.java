package com.example.restaurant.services.servicesImp;

import com.example.restaurant.pojo.Restaurant;
import com.example.restaurant.pojo.SignUpInfo;
import com.example.restaurant.repository.LoginRepository;
import com.example.restaurant.repository.RegistrationRepository;
import com.example.restaurant.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class RegistrationServiceImp implements RegistrationService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    RegistrationRepository registrationRepository;

    public void restaurantRegister(SignUpInfo signUpInfo){
        Restaurant restaurant = registrationRepository.findRestaurantByName(signUpInfo.getRestaurantName());
        if (restaurant != null){
            System.out.println("The restaurant name is existed");
        }else{
            Restaurant newRestaurant = new Restaurant(signUpInfo.getRestaurantName());
            registrationRepository.save(newRestaurant);

        }
    }
}
