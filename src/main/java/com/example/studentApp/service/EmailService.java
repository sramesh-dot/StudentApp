package com.example.studentApp.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Async
    public void sendEmail() {

        System.out.println(
                "Email started: "
                        + Thread.currentThread().getName()
        );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Email completed: "
                        + Thread.currentThread().getName()
        );
    }
}


