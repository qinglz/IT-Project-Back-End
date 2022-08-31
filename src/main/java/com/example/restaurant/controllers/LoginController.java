package com.example.restaurant.controllers;

import com.example.restaurant.pojo.Booking;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @GetMapping("findAllBooking")
    public List<Booking> getAllBooking(){
        return loginService.getAllBooking();

    }
    @GetMapping("/verifyAccount")
    public boolean loginCheck(@RequestParam("email")String email, @RequestParam("password")String password){
        return loginService.verifyUser(email,password);

    }

}
