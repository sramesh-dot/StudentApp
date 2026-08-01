package com.example.demo.service;

import com.example.demo.config.ConfigurationProperties;
import com.example.demo.entity.College;
import com.example.demo.entity.Student;
import com.example.demo.exception.CollegeNotFoundException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.CollegeRepository;
import com.example.demo.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    //Configuration properties injection *********************************************************************
    /*@Value("${college.name}")
    private String collegeName;

    @Value("${college.city}")
    private String city;

    @Value("${college.code}")
    private int code;*/

    /*Using @ConfigurationProperties Annotation
    private ConfigurationProperties properties;

    public StudentService(ConfigurationProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void printCollegeInfo() {
        System.out.println(properties.getName());
        System.out.println(properties.getCity());
        System.out.println(properties.getCode());
    }
    End of configuration properties *************************************************************************/

    //Initializing variables
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    //Constructor Injection
    public StudentService(StudentRepository studentRepository, CollegeRepository collegeRepository) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                                 new //RuntimeException("Test"));
                                         StudentNotFoundException("Student with ID " + id + " not found"));
    }

    public College getCollegeById(int id) {
        return collegeRepository.findById(id)
                .orElseThrow(() ->
                        new CollegeNotFoundException("College with ID " + id + " not found"));
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(int id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    ;

    public List<Student> findByAgeGreaterThan(int age) {
        return studentRepository.findByAgeGreaterThan(age);
    }

    ;

    public List<Student> findByNameContaining(String keyword) {
        return studentRepository.findByNameContaining(keyword);
    }

    ;

    public List<Student> findByNameStartingWith(String prefix) {
        return studentRepository.findByNameStartingWith(prefix);
    }

    ;

    public boolean existsByName(String name) {
        return studentRepository.existsByName(name);
    }

    ;

    public long countByAge(int age) {
        return studentRepository.countByAge(age);
    }

    ;

    public List<Student> getStudentsOlderThan(int age) {
        return studentRepository.getStudentsOlderThan(age);
    }

    ;

    public List<Student> getStudentsSortedByAge() {
        return studentRepository.getStudentsSortedByAge();
    }

    ;

    public long getStudentCount() {
        return studentRepository.getStudentCount();
    }

    ;


    //Spring Data JPA Pagination
    public Page<Student> getStudents(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository.findAll(pageable);
    }

    //Sorting
    public List<Student> getStudentsSorted() {

        return studentRepository.findAll(
                Sort.by("age").descending());

    }

    //Pagination & Sorting Together
    public Page<Student> getPS(int page, int size) {
        Pageable pageable =
                PageRequest.of(
                        0,
                        5,
                        Sort.by("age").descending());

        return studentRepository.findAll(pageable);
    }

    @Transactional
    public void addStudentAndCollege() {

        College college = new College();
        college.setId(100);
        college.setName("ABC College");

        collegeRepository.save(college);

        Student student = new Student();
        student.setId(100);
        student.setName("Ramesh");
        student.setAge(24);

        studentRepository.save(student);
    }
}