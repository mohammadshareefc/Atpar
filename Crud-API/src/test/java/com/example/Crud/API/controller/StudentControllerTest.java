package com.example.Crud.API.controller;

import com.example.Crud.API.entity.Students;
import com.example.Crud.API.service.Studentservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Studentservice studentservice;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testAddStudent() throws Exception {
        Students student = new Students(1, "John Doe", 20, "Computer Science");

        when(studentservice.addstudent(any(Students.class))).thenReturn(student);

        mockMvc.perform(post("/students/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"John Doe\", \"age\": 20, \"dept\": \"Computer Science\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.dept").value("Computer Science"));

        verify(studentservice, times(1)).addstudent(any(Students.class));
    }

    @Test
    void testGetAllStudents() throws Exception {
        List<Students> studentsList = Arrays.asList(
                new Students(1, "John Doe", 20, "Computer Science"),
                new Students(2, "Jane Smith", 22, "Electrical Engineering")
        );

        when(studentservice.getAllStudents()).thenReturn(studentsList);

        mockMvc.perform(get("/students/getStudents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].dept").value("Computer Science"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].dept").value("Electrical Engineering"));

        verify(studentservice, times(1)).getAllStudents();
    }

    @Test
    void testUpdateStudent() throws Exception {
        Students updatedStudent = new Students(1, "John Doe", 21, "Computer Science");

        when(studentservice.updateStudent(any(Students.class))).thenReturn(updatedStudent);

        mockMvc.perform(post("/students/updateStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"John Doe\", \"age\": 21, \"dept\": \"Computer Science\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.dept").value("Computer Science"));

        verify(studentservice, times(1)).updateStudent(any(Students.class));
    }

    @Test
    void testDeleteStudent() throws Exception {
        int studentId = 1;
        String successMessage = "Student deleted successfully";

        when(studentservice.deleteStudent(studentId)).thenReturn(successMessage);

        mockMvc.perform(delete("/students/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(content().string(successMessage));

        verify(studentservice, times(1)).deleteStudent(studentId);
    }
}
