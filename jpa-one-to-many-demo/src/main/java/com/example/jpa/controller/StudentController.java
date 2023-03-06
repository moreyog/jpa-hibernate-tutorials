package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Comment;
import com.example.jpa.model.Student;
import com.example.jpa.repository.CommentRepository;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.StudentRepository;
import com.example.jpa.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/students")
    public Page<Student> getAllStudent(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable (value = "studentId") Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student " + studentId + " not found"));
    }

    @PostMapping("/students/{universityId}")
    public Student createStudent(@PathVariable (value = "universityId") Long universityId,@Valid @RequestBody Student student) {
           // return studentRepository.save(student);
        return universityRepository.findById(universityId).map(university -> {
            student.setUniversity(university);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("universityId " + universityId + " not found"));
    }

    @PutMapping("/students")
    public Student updateStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable (value = "studentId") Long studentId) {

       return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("studentId " + studentId));

    }

}
