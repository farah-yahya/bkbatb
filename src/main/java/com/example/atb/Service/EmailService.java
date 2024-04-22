package com.example.atb.Service;

import com.example.atb.Entities.User;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
    void sendEmailWithTemplate(User user);
}
