package com.example.cms.controller;

import com.example.cms.controller.dto.StudentDTO;
import com.example.cms.controller.exceptions.ProfessorNotFoundException;
import com.example.cms.controller.exceptions.StudentNotFoundException;
import com.example.cms.model.entity.Course;
import com.example.cms.model.entity.Professor;
import com.example.cms.model.entity.Student;
import com.example.cms.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class StudentController {
    @Autowired
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/students")
    List<Student> retrieveAllStudents() {
        return repository.findAll();
    }

    @PostMapping("/students")
    Student createStudent(@RequestBody Student newStudent) {
        return repository.save(newStudent);
    }

    @GetMapping("/students/{id}")
    Student retrieveStudent(@PathVariable("id") Long studentId) {
        return repository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

//    @PutMapping("/students/{id}")
//    Student updateStudent(@RequestBody Student newStudent, @PathVariable("id") Long studentId) {
//        return repository.findById(studentId)
//                .map(student -> {
//                    student.setFirstName(newStudent.getFirstName());
//                    student.setLastName(newStudent.getLastName());
//                    student.setEmail(newStudent.getEmail());
//                    return repository.save(student);
//                })
//                .orElseGet(() -> {
//                    newStudent.setId(studentId);
//                    return repository.save(newStudent);
//                });
//    }


    @PutMapping("/students/{id}")
    Student updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable("student") Long studentId) {
        return repository.findById(studentId)
                .map(student -> {
                    student.setFirstName(studentDTO.getFirstName());
                    student.setLastName(studentDTO.getLastName());
                    student.setEmail(studentDTO.getEmail());
                    return repository.save(student);
                })
                .orElseGet(() -> {
                    Student newStudent = new Student();
                    newStudent.setId(studentId);
                    return repository.save(newStudent);
                });
    }


    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable("id") Long studentId) {
        repository.deleteById(studentId);
    }

    @GetMapping("/students/search/{searchstring}")
    List<Student> searchStudent(@PathVariable("searchstring") String searchString) {
        return repository.search(searchString);
    }

    @GetMapping("/students/top")
    List<Student> retrieveTopStudents() {
        return repository.findTopStudents();
    }
}
