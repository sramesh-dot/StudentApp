package com.example.studentApp.listener;

import com.example.studentApp.event.StudentCreatedEvent;
import com.example.studentApp.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener {

    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        System.out.println("Email sent for student:" + event.getStudent().getName());
    }
}
