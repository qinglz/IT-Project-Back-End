package com.example.restaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    private String to;
    private String message;
    private String subject;


    public String getRecipient() {
        return to;
    }

    public void setRecipient(String recipient) {
        this.to = recipient;
    }

    public String getMsgBody() {
        return message;
    }

    public void setMsgBody(String msgBody) {
        this.message = msgBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
