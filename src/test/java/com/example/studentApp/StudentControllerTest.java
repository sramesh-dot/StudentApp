package com.example.studentApp;

import com.example.studentApp.controller.StudentController;
import com.example.studentApp.entity.Student;
import com.example.studentApp.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    StudentService service;

    @Test
    void shouldReturnAllStudents() throws Exception {

        List<Student> student = List.of(new Student (1, "Ram", 19), new Student (2, "Esh", 26));

        when(service.getAllStudents())
                .thenReturn(student);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ram"));
    }

    @Test
    void shouldReturnStudentById() throws Exception {

        Student student = new Student (1, "Ram", 19);

        when(service.getStudentById(1))
                .thenReturn(student);

        mockMvc.perform(get("/students/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ram"));
    }

    @Test
    void shouldSaveStudent() throws Exception {

        Student student = new Student (1, "Ram", 19);

        when(service.saveStudent(any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                            .writeValueAsString(student)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ram"));
    }

    @Test
    void shouldDeleteStudent() throws Exception {

        mockMvc.perform(delete("/students/{id}", 1))
                       .andExpect(status().isNoContent());
    }

    @Test
    void shouldEditStudent() throws Exception {

        Student student = new Student(1, "Ram", 19);

        when(service.updateStudent(eq(1), any(Student.class)))
                .thenReturn(student);

        mockMvc.perform(put("/students/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ram"));
    }
}
