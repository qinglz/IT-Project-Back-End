package com.example.restaurant.services;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;

public interface LoginService {
    public Result userLogin(BusinessUser businessUser);
    public Result userSignUp(BusinessUser businessUser);
}
