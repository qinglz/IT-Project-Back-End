package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;


    @PostMapping("/verifyAccount")
    @ResponseBody
    public Result<BusinessUser> login(@RequestBody Map<String, String
                > loginInfo){
        return loginService.userLogin(loginInfo);


    }

    @PostMapping("/signUpAccount")
    public Result<BusinessUser> signUpAccount(@RequestBody Map<String, String> signupInfo){
        return loginService.userSignUp(signupInfo);

    }

}
