package com.example.Crud.API.service;

import com.example.Crud.API.entity.Students;
import com.example.Crud.API.repository.Studentrepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentserviceTest {

    @Mock
    private Studentrepo studentrepo;

    @InjectMocks
    private Studentservice studentservice;
    private Object studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        // Arrange
        Students student = new Students(1, "John Doe", 20, "Computer Science");
        when(studentrepo.save(any(Students.class))).thenReturn(student);

        // Act
        Students result = studentservice.addstudent(student);

        // Assert
        assertEquals(student.getId(), result.getId());
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getAge(), result.getAge());
        assertEquals(student.getDept(), result.getDept());

        verify(studentrepo, times(1)).save(any(Students.class));
    }

    @Test
    void testGetAllStudents() {
        // Arrange
        List<Students> studentsList = Arrays.asList(
                new Students(1, "John Doe", 20, "Computer Science"),
                new Students(2, "Jane Smith", 22, "Electrical Engineering")
        );
        when(studentrepo.findAll()).thenReturn(studentsList);

        // Act
        List<Students> result = studentservice.getAllStudents();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        verify(studentrepo, times(1)).findAll();
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        Students existingStudent = new Students(1, "John Doe", 20, "Computer Science");
        Students updatedStudent = new Students(1, "John Doe", 21, "Computer Science");

        when(studentrepo.findById(existingStudent.getId())).thenReturn(Optional.of(existingStudent));
        when(studentrepo.save(any(Students.class))).thenReturn(updatedStudent);

        // Act
        Students result = studentservice.updateStudent(updatedStudent);

        // Assert
        assertEquals(21, result.getAge());
        verify(studentrepo, times(1)).save(any(Students.class));
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        int studentId = 1;
        doNothing().when(studentrepo).deleteById(studentId);



        // Act
        studentservice.deleteStudent(studentId);

        // Assert
        verify(studentrepo, times(1)).deleteById(studentId);
    }
}
