package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;


    @GetMapping("/verifyAccount")
    public Result<BusinessUser> login(@RequestParam("email")String email, @RequestParam("password")String password){
        return loginService.userLogin(email,password);


    }

    @PostMapping("/signUpAccount")
    public Result<BusinessUser> signUpAccount(@RequestBody BusinessUser businessUser){
        return loginService.userSignUp(businessUser);

    }

}
