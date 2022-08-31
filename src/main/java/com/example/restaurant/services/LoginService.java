package com.example.restaurant.services;


import com.example.restaurant.pojo.Booking;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.pojo.SignUpInfo;
import com.example.restaurant.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private static Logger logger= LoggerFactory.getLogger(LoginService.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    LoginRepository loginRepository;

    public List<Booking> getAllBooking(){

        return mongoTemplate.findAll(Booking.class);
    }

    public boolean verifyUser(String email, String password){
        BusinessUser businessUser = loginRepository.findBusinessUserByEmail(email);
        if(businessUser==null){
            return false;
        }else if(!businessUser.getPassword().equals(password)){
            return false;
        }else {
            return true;
        }

    }
    public boolean verifySignUp(SignUpInfo signUpInfo){
        BusinessUser businessUser = loginRepository.findBusinessUserByEmail(signUpInfo.getEmail());
        if(businessUser != null){
            return false;
        }else {
            BusinessUser newBusinessUser = new BusinessUser(signUpInfo.getEmail(),signUpInfo.getPassword());
            loginRepository.insert(newBusinessUser);
            return true;
        }
    }





}
