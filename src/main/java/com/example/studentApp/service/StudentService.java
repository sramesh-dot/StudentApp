package com.example.studentApp.service;

import com.example.studentApp.dto.StudentResponseDTO;
import com.example.studentApp.entity.College;
import com.example.studentApp.entity.Student;
import com.example.studentApp.event.StudentCreatedEvent;
import com.example.studentApp.exception.CollegeNotFoundException;
import com.example.studentApp.exception.StudentNotFoundException;
import com.example.studentApp.mapper.StudentMapper;
import com.example.studentApp.repository.CollegeRepository;
import com.example.studentApp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final ApplicationEventPublisher eventPublisher;

    //Constructor Injection
    public StudentService(StudentRepository studentRepository, CollegeRepository collegeRepository, ApplicationEventPublisher eventPublisher) {
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
        this.eventPublisher = eventPublisher;
    }

    public Student saveStudent(Student student) {
        logger.info("Saving student: {}", student.getName());
        Student savedStudent = studentRepository.save(student);
        eventPublisher.publishEvent(new StudentCreatedEvent(savedStudent));
        logger.info("Student saved with ID: {}", savedStudent.getId());
        return savedStudent;
    }

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    public List<Student> getAllStudents() {
        logger.info("Fetching All Students");
        List<Student> studentList = studentRepository.findAll();
        logger.info("Fetched {} students", studentList.size());
        return studentList;
    }

    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(int id) {
        logger.debug("Fetching student with ID {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Student not found with ID {}", id);
                    return new StudentNotFoundException(
                            "Student with ID " + id + " not found"
                    );
                });
    }

    public College getCollegeById(int id) {
        return collegeRepository.findById(id)
                .orElseThrow(() ->
                        new CollegeNotFoundException("College with ID " + id + " not found"));
    }

    @CacheEvict(value = "students", key = "#id")    //Update database and remove cache
    public void deleteStudent(int id) {
        logger.info("Trying to delete student with ID {}", id);
        studentRepository.deleteById(id);
        logger.info("Student with ID {} Deleted", id);
    }

    @CachePut(value = "students", key = "#id")    //Update both database and cache
    public Student updateStudent(int id, Student student) {
        logger.debug("Trying to update student to ID {}", id);
        student.setId(id);
        Student updatedStudent = studentRepository.save(student);
        logger.info("Student with ID {} updated successfully", id);
        return updatedStudent;
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
    public Page<StudentResponseDTO> getStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(StudentMapper::toResponse);
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

    //SLF4J API - Logback - Logger
    //Create a Logger
    private static final Logger logger = LoggerFactory.getLogger(
            StudentService.class);
}