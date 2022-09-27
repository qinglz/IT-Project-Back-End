package com.example.restaurant.utils;

import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public Result sendEmail(EmailDetails emailDetails){
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailDetails.getTo());
            simpleMailMessage.setText(emailDetails.getMessage());
            simpleMailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(simpleMailMessage);
            return Result.success("Email sent successfully!");
        }
        catch(Exception e){
            return Result.error("Error while sending email");
        }
    }
}
