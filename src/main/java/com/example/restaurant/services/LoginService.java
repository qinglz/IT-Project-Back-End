package com.example.restaurant.services;

import com.example.restaurant.Result;

public interface LoginService {
    public Result userLogin(String email, String password);
    public Result userSignUp(String name, String email, String password);
}
