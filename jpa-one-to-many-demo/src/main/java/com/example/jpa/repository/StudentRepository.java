package com.example.jpa.repository;

import com.example.jpa.model.Post;
import com.example.jpa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository  extends JpaRepository<Student, Long> {
}

