package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.pojo.Booking;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.pojo.SignUpInfo;
import com.example.restaurant.services.LoginService;
import com.example.restaurant.services.servicesImp.LoginServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @GetMapping("findAllBooking")
    public Result<List<Booking>> getAllBooking(){
        return Result.success(loginService.getAllBooking());

    }
    @GetMapping("verifyAccount")
    public Result<BusinessUser> login(@RequestParam("email")String email, @RequestParam("password")String password){
        BusinessUser businessUser = loginService.userLogin(email,password);
        if(businessUser!=null){
            return Result.success(loginService.userLogin(email,password));
        }
        return Result.error("Incorrect Password Or Email Doesn't Exist");


    }

    /* what should I return?*/
    @PostMapping("signUpAccount")
    public Result<BusinessUser> signUpAccount(@RequestBody SignUpInfo signUpInfo){
        if(loginService.userSignUp(signUpInfo)){
            BusinessUser businessUser = loginService.userLogin(signUpInfo.getEmail(), signUpInfo.getPassword());
            return Result.success(businessUser);
        }
        return Result.error("This Email Has Been Used To Create An Account");

    }

}
