package com.example.studentApp;

import com.example.studentApp.entity.Student;
import com.example.studentApp.repository.StudentRepository;
import com.example.studentApp.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository repository;

    @InjectMocks
    StudentService service;

    @Test
    void shouldReturnStudent() {

        Student student =
                new Student(1, "Ramesh", 24);

        when(repository.findById(1))
                .thenReturn(Optional.of(student));

        Student result =
                service.getStudentById(1);

        assertEquals("Ramesh",
                result.getName());

        verify(repository).findById(1);

    }

    @Test
    void shouldSaveStudent() {

        Student student =
                new Student(1, "Ramesh", 24);

        when(repository.save(student))
                .thenReturn(student);

        Student saved = service.saveStudent(student);

        assertNotNull(saved);

        assertEquals("Ramesh",
                saved.getName());

        verify(repository).save(student);
    }

    @Test
    void shouldReturnAllStudents() {

        List<Student> student = List.of((new Student(1, "Ram", 45)), new Student(2, "Esh", 33));

        when(repository.findAll())
                .thenReturn(student);

        List<Student> getAll = service.getAllStudents();

        assertEquals(2, getAll.size());

        verify(repository).findAll();
    }

    @Test
    void shouldReturnStudentWhenIdExists() {

        Student student =
                new Student(1,"Ramesh",24);

        when(repository.findById(1))
                .thenReturn(Optional.of(student));

        Student result =
                service.getStudentById(1);

        assertEquals("Ramesh",
                            result.getName());

        verify(repository).findById(1);

    }

    @Test
    void shouldDeleteStudent() {

        service.deleteStudent(5);

        verify(repository)
                .deleteById(5);

    }

    @Test
    void shouldUpdateStudent() {

        Student student =
                new Student(1,"Updated",25);

        when(repository.save(student))
                .thenReturn(student);

        Student result =
                service.updateStudent(1, student);

        assertEquals("Updated",
                result.getName());

        verify(repository).save(student);

    }

    @Test
    void shouldReturnTrueIfStudentExists() {

        when(repository.existsByName("Ramesh"))
                .thenReturn(true);

        boolean exists =
                service.existsByName("Ramesh");

        assertTrue(exists);

    }

    @Test
    void shouldReturnCountByAge() {

        when(repository.countByAge(24))
                .thenReturn(1L);

        long count = service.countByAge(24);

        assertEquals(1L, count);
    }
}
