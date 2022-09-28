package com.example.restaurant.services.servicesImp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceImpTest {
    @Autowired
    LoginServiceImp loginServiceImp;

    @Test
    void userLogin() {
        Map<String,String> info = new HashMap<>();
        info.put("email","123@163.com");
        info.put("password","1521452");
        System.out.println(loginServiceImp.userLogin(info));

    }

    @Test
    void userLogout() {
        loginServiceImp.userLogout();
    }

    @Test
    void userSignUp() {
        Map<String,String> info = new HashMap<>();
        info.put("email","123@163.com");
        info.put("password","1521452");
        System.out.println(loginServiceImp.userSignUp(info));


    }
}