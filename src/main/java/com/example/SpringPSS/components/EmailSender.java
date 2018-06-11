package com.example.SpringPSS.components;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
