package com.pushpendra.demo.model;

import org.springframework.stereotype.Component;

@Component
public class EmailRequest {

    private String emailText;
    private String senderName;
    private String tone;


    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }
}
