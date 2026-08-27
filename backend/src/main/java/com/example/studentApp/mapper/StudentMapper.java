package com.example.studentApp.mapper;

import com.example.studentApp.dto.StudentRequestDTO;
import com.example.studentApp.dto.StudentResponseDTO;
import com.example.studentApp.entity.Student;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setAge(dto.getAge());

        return student;
    }

    public static StudentResponseDTO toResponse(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());

        return dto;
    }
}
