package com.example.studentApp.event;

import com.example.studentApp.entity.Student;

public class StudentCreatedEvent {

    private final Student student;

    public StudentCreatedEvent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
