package com.example.esdProject.controller;

import java.util.*;
import com.example.esdProject.entity.Student;
import com.example.esdProject.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public List<Student> getAllStudentsList(){
        return service.getAllStudentList();
    }

}
