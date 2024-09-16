package com.example.Crud.API.service;

import com.example.Crud.API.entity.Students;
import com.example.Crud.API.repository.Studentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {
@Autowired
private Studentrepo studentrepo;
    public Students addstudent(Students student){
return studentrepo.save(student);
    }

    public List<Students> getAllStudents(){
        return studentrepo.findAll();
    }
  public Students updateStudent(Students updatedstudents){
        Optional <Students> student1 = studentrepo.findById(updatedstudents.getId());
        Students student = student1.get();
        student.setAge(updatedstudents.getAge());
        student.setDept(updatedstudents.getDept());
        student.setName(updatedstudents.getName());
        return studentrepo.save(student);
  }
    public String deleteStudent(int id) {
        studentrepo.deleteById(id);  // Deletes by id
        return "Student deleted successfully";
    }
}
