package com.example.restaurant.services.servicesImp;

import com.example.restaurant.Result;
import com.example.restaurant.entities.EmailDetails;
import com.example.restaurant.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImp implements EmailService {


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
            return Result.error("Erro while sending email");
        }
    }

//    public String sendMailWithAttachment(EmailDetails details)
//    {
//        // Creating a mime message
//        MimeMessage mimeMessage
//                = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//            // Setting multipart as true for attachments to
//            // be send
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
//            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);
//
//            // Sending the mail
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        }
//
//        // Catch block to handle MessagingException
//        catch (MessagingException e) {
//
//            // Display message when exception occurred
//            return "Error while sending mail!!!";
//        }
//    }
}

