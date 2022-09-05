package com.example.restaurant.services;

import com.example.restaurant.pojo.Booking;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.pojo.SignUpInfo;

import java.util.List;

public interface LoginService {
    public List<Booking> getAllBooking();
    public BusinessUser userLogin(String email, String password);
    public boolean userSignUp(SignUpInfo signUpInfo);
}
