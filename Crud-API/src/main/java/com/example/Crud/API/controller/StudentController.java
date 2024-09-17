package com.example.Crud.API.controller;

import com.example.Crud.API.entity.Students;
import com.example.Crud.API.service.Studentservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Student API", description = "Operations related to students")
public class StudentController {

    @Autowired
    private Studentservice studentservice;

    @PostMapping("/addStudent")
    @Operation(summary = "Add a new student", description = "Creates a new student in the system")
    public ResponseEntity<Students> addStudent(@RequestBody Students student) {
        return ResponseEntity.ok(studentservice.addstudent(student));
    }

    @GetMapping("/getStudents")
    @Operation(summary = "Get all students", description = "Retrieves a list of all students")
    public ResponseEntity<List<Students>> getAllStudents() {
        return ResponseEntity.ok(studentservice.getAllStudents());
    }

    @PostMapping("/updateStudent")
    @Operation(summary = "Update an existing student", description = "Updates the details of an existing student")
    public ResponseEntity<Students> updateStudent(@RequestBody Students student) {
        return ResponseEntity.ok(studentservice.updateStudent(student));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a student", description = "Deletes a student from the system by ID")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        return ResponseEntity.ok(studentservice.deleteStudent(id));
    }
}
