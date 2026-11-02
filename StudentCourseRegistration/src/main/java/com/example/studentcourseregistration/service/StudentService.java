package com.example.studentcourseregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.studentcourseregistration.model.Student;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    private AtomicLong counter = new AtomicLong(1);

    // 1. Register Student
    public Student registerStudent(Student student) {
        student.setStudentId(counter.getAndIncrement());
        students.add(student);
        return student;
    }

    // 2. View All Students
    public List<Student> getAllStudents() {
        return students;
    }

    // 3. Find Student By ID
    public Student getStudentById(Long id) {
        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // 4. Delete Student
    public boolean deleteStudent(Long id) {
        return students.removeIf(
            student -> student.getStudentId().equals(id)
        );
    }

    // 5. Search By Student Name
    public List<Student> getStudentsByName(String name) {
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getStudentName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }

    // 6. Search By Course Name
    public List<Student> getStudentsByCourse(String course) {
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getCourseName().equalsIgnoreCase(course)) {
                result.add(student);
            }
        }

        return result;
    }
}