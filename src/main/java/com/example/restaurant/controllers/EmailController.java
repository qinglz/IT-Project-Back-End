package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;
import com.example.restaurant.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private EmailService emailService;


    @PostMapping("/send-email")
    public Result sendEmail(@RequestBody EmailDetails emailDetails){
        Result status = emailService.sendEmail(emailDetails);
        return Result.success(status);
    }
}
