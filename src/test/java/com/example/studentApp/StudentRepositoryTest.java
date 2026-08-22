package com.example.studentApp;

import com.example.studentApp.entity.Student;
import com.example.studentApp.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void shouldSaveStudent() {
        Student student = new Student(1, "Pam", 25);

        Student saved = repository.save(student);

        assertEquals("Pam", saved.getName());
        assertEquals(25, saved.getAge());
    }

    @Test
    void shouldFindStudentByName() {
        Student student = new Student(1, "Ram", 25);

        Student saved = repository.save(student);

        List<Student> result = repository.findByName("Ram");

        assertEquals("Ram", result.get(0).getName());
        assertEquals(1, result.size());
    }

    @Test
    void shouldFindStudentByAge() {

        repository.save(new Student(1, "Ram", 25));
        repository.save(new Student(2, "Raina", 22));

        List<Student> result = repository.findByAge(22);

        assertEquals("Raina", result.get(0).getName());
        assertEquals(1, result.size());
    }

    @Test
    void shouldFindStudentsOlderThan() {

        repository.save(
                new Student(1, "Ramesh", 24)
        );

        repository.save(
                new Student(2, "John", 22)
        );

        repository.save(
                new Student(3, "David", 30)
        );

        List<Student> result =
                repository.findByAgeGreaterThan(23);

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindStudentsContainingKeyword() {
        repository.save(
                new Student(1, "Ramesh", 24)
        );

        repository.save(
                new Student(2, "John", 22)
        );

        repository.save(
                new Student(3, "David", 30)
        );

        List<Student> result =
                repository.findByNameContaining("Jo");

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void shouldCheckIfStudentExists() {
        repository.save(
                new Student(1, "Ramesh", 24)
        );

        repository.save(
                new Student(2, "John", 22)
        );

        repository.save(
                new Student(3, "David", 30)
        );

        boolean result =
                repository.existsByName("John");

        assertTrue(result);
    }

    @Test
    void shouldCountStudentsByAge() {
        repository.save(
                new Student(1, "Ramesh", 24)
        );

        repository.save(
                new Student(2, "John", 30)
        );

        repository.save(
                new Student(3, "David", 30)
        );

        long result =
                repository.countByAge(30);

        assertEquals(2, result);
    }
}
