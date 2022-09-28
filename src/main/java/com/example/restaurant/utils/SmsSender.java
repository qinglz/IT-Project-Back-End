package com.example.restaurant.utils;

import com.example.restaurant.entities.SMSDetails;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {

    @Value("${TWILIO_ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String AUTH_TOKEN;

    @Value("${Twilio_number}")
    private String TWI_NUMBER;



    public void sendSMS(SMSDetails smsDetails) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(smsDetails.getTo()),
                TWI_NUMBER, smsDetails.getMessage()).create();

        System.out.println("Here is my id: " + message.getSid());


    }

}
