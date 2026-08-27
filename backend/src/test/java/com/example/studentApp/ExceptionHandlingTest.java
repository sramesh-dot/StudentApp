package com.example.studentApp;

import com.example.studentApp.controller.StudentController;
import com.example.studentApp.entity.Student;
import com.example.studentApp.exception.StudentNotFoundException;
import com.example.studentApp.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;



@WebMvcTest(StudentController.class)
public class ExceptionHandlingTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    StudentService service;

    @Test
    void forStudentNotFound() throws Exception {

        when(service.getStudentById(999))
                .thenThrow(
                        new StudentNotFoundException(
                                "Student with ID 999 not found"
                        )
                );

        mockMvc.perform(get("/students/{id}", 999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Student with ID 999 not found"))
                .andExpect(jsonPath("$.path")
                        .value("/students/999"));
    }

    @Test
    void forInvalidStudent() throws Exception {

        String json = """
                {
                    "id": 1,
                    "name": "",
                    "age": 15
                }
                """;

        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status")
                        .value(400));

        verify(service, never())
                .saveStudent(any(Student.class));
    }

    @Test
    void shouldRejectInvalidDataType() throws Exception {

        String json = """
                {
                    "id": 1,
                    "name": "Ram",
                    "age": "hello"
                }
                """;

        mockMvc.perform(
                        post("/students")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status")
                        .value(400));
    }
}
