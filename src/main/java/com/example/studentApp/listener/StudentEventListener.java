package com.example.studentApp.listener;

import com.example.studentApp.event.StudentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        System.out.println("Student created: " + event.getStudent().getName());
    }
}
