package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.University;
import com.example.jpa.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/universities")
    public Page<University> getAllUniversity(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }
    @GetMapping("/universities/{universityId}")
    public University getUniversityById(@PathVariable (value = "universityId") Long universityId,Pageable pageable) {
        return universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University " + universityId + " not found"));
    }

    @PostMapping("/universities/{universityId}")
    public University getUniversityById(@PathVariable (value = "universityId") Long universityId) {
        return universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University " + universityId + " not found"));
    }

    @PostMapping("/universities")
    public University createUniversity(@Valid @RequestBody University university) {
            return universityRepository.save(university);
    }

    @PutMapping("/universities")
    public University updateUniversity(@Valid @RequestBody University university) {
        return universityRepository.save(university);
    }

    @DeleteMapping("/universities/{universityId}")
    public ResponseEntity<?> deleteUniversity(@PathVariable (value = "universityId") Long universityId) {

       return universityRepository.findById(universityId).map(university -> {
            universityRepository.delete(university);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("universityId " + universityId));

    }

}
