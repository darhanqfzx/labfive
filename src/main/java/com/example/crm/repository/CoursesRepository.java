package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crm.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> { }
