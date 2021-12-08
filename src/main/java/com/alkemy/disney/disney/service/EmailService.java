package com.alkemy.disney.disney.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendWelcomeEmailTo(String to);
}
