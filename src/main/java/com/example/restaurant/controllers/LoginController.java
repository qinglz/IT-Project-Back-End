package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
//    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;


    /**
     * This API is for Post HttpRequest, and request a Body with format as following:
     * @param loginInfo {"email":value,"password":value}
     * @return Result show if login successfully. If login successfully, result will contain a token in Result.data.
     */
    @PostMapping("/verifyAccount")
    @ResponseBody
    public Result<BusinessUser> login(@RequestBody Map<String, String> loginInfo){
        return loginService.userLogin(loginInfo);

    }

    @PostMapping("/signOutAccount")
    public Result logout(){
        return loginService.userLogout();
    }

    /**
     * This API is for Post HttpRequest, and request a body with format as following:
     * @param signupInfo {"email":value,"password":value,"fullName":value}.
     * @return Result show if sign up successfully. If sign up successfully, this method will automatically call login method
     * and result will contain a token in Result.data.
     */
    @PostMapping("/signUpAccount")
    public Result<BusinessUser> signUpAccount(@RequestBody Map<String, String> signupInfo){
        return loginService.userSignUp(signupInfo);

    }

}
