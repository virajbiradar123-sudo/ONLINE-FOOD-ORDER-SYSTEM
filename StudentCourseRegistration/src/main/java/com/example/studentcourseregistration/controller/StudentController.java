package com.example.studentcourseregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.studentcourseregistration.model.Student;
import com.example.studentcourseregistration.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;


    // 1. Register Student

    @PostMapping
    public ResponseEntity<Student> registerStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = service.registerStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedStudent);
    }


    // 2. View All Students

    @GetMapping
    public List<Student> getAllStudents() {

        return service.getAllStudents();
    }


    // 3. Find Student By ID

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable Long id) {

        Student student = service.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }


    // 4. Delete Student

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable Long id) {

        boolean deleted = service.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                "Student registration deleted successfully");
    }


    // 5. Search By Student Name

    @GetMapping("/name/{name}")
    public List<Student> getStudentsByName(
            @PathVariable String name) {

        return service.getStudentsByName(name);
    }


    // 6. Search By Course Name

    @GetMapping("/course/{course}")
    public List<Student> getStudentsByCourse(
            @PathVariable String course) {

        return service.getStudentsByCourse(course);
    }

}