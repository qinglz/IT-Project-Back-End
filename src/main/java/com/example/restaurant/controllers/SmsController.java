package com.example.restaurant.controllers;

import com.example.restaurant.Result;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.rest.api.v2010.account.Message;


@RestController
public class SmsController {

    @GetMapping(value = "/sendSMS")
    public Result sendSMS(){
        Twilio.init(System.getenv("TWILIO_ACCOUNT_SID"), System.getenv("TWILIO_AUTH_TOKEN"));

        Message.creator(new PhoneNumber("<TO number - ie your cellphone>"),
                new PhoneNumber("<FROM number - ie your Twilio number"), "Hello from Twilio 📞").create();

        return Result.success("Message sent successfully!");
    }
}
