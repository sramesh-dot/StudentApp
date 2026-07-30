package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class LoggerService {

    private final StudentService service;

    public LoggerService(StudentService service) {
        this.service = service;
    }

    public void log(String message) {
        System.out.println(message);
    }
}
