package com.example.studentApp.repository;

import com.example.studentApp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByName(String name);

    List<Student> findByAge(int age);

    List<Student> findByAgeGreaterThan(int age);

    List<Student> findByNameContaining(String keyword);

    List<Student> findByNameStartingWith(String prefix);

    boolean existsByName(String name);

    long countByAge(int age);

    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> getStudentsOlderThan(@Param("age") int age);

    @Query("SELECT s FROM Student s ORDER BY s.age DESC")
    List<Student> getStudentsSortedByAge();

    @Query("SELECT COUNT(s) FROM Student s")
    long getStudentCount();

}