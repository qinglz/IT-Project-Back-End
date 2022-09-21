package com.example.restaurant.services;

import com.example.restaurant.Result;

import java.util.Map;

public interface LoginService {
    public Result userLogin(Map<String,String> loginInfo);

    public Result userLogout();
    public Result userSignUp(Map<String, String> signupInfo);
}
