package com.example.studentApp.repository;

import com.example.studentApp.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College, Integer> {
    }

