package com.example.jpa.repository;

import com.example.jpa.model.Student;
import com.example.jpa.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository  extends JpaRepository<University, Long> {
}
