package com.example.demo.controller;

import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {

        Student savedStudent = service.saveStudent(student);

        return ResponseEntity.status(201).body(savedStudent);
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {

        service.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id,
                                 @RequestBody Student student){

        return service.updateStudent(id, student);

    }

    @GetMapping("/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return service.getStudentsByName(name);
    }

    @GetMapping("/age/{age}")
    public  List<Student> findByAge(@PathVariable int age) {
        return service.findByAge(age);
    };

    @GetMapping("/age/greater/{age}")
    public List<Student> findByAgeGreaterThan(@PathVariable int age) {
        return service.findByAgeGreaterThan(age);
    };

    @GetMapping("/keyword/{keyword}")
    public List<Student> findByNameContaining(@PathVariable String keyword) {
        return service.findByNameContaining(keyword);
    };

    @GetMapping("/prefix/{prefix}")
    public List<Student> findByNameStartingWith(@PathVariable String prefix) {
        return service.findByNameStartingWith(prefix);
    };

    @GetMapping("/exists/{name}")
    public boolean existsByName(@PathVariable String name) {
        return service.existsByName(name);
    }

    @GetMapping("/countbyage/{age}")
    public long countByAge(@PathVariable int age) {
        return service.countByAge(age);
    };

    @GetMapping("/ageolderthan/{age}")
    public List<Student> getStudentsOlderThan(@PathVariable("age") int age) {
        return service.getStudentsOlderThan(age);
    };

    @GetMapping("/sortbyagedesc")
    public List<Student> getStudentsSortedByAge() {
        return service.getStudentsSortedByAge();
    };

    @GetMapping("/countnumberofstudents")
    public long getStudentCount() {
        return service.getStudentCount();
    };

    //Spring Data JPA Pagination
    @GetMapping("/page")
    public Page<StudentResponseDTO> getStudents(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getStudents(page, size);
    }

    //Sorting
    @GetMapping("/sortdescending")
    public List<Student> getStudentsSorted() {

        return service.getStudentsSorted();

    }

    //Pagination & Sorting Together
    @GetMapping("/page?page=0&size=5")
    public Page<Student> getPS(
        @RequestParam int page,
        @RequestParam int size) {

        return service.getPS(page, size);
    }
}