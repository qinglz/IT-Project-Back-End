package com.example.restaurant.services;


import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;

public interface EmailService {
    public Result sendEmail(EmailDetails details);

}
