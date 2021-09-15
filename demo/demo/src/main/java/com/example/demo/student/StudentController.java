package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// API layer or presentation layer

@RestController
// route --> http://localhost:8080/api/v1/student
@RequestMapping(path = "api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping // GET
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping // POST
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}") // DELETE
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}") // PUT
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
       studentService.updateStudent(studentId, name, email);
    }

}

// POST generated request
// POST http://localhost:8080/api/v1/student
// Content-Type: application/json
//
// {
// "name": "Bilal",
// "email": "bilal.ahmed@gmail.com",
// "dob": "1995-12-17"
// }

// DELETE generated request
// DELETE http://localhost:8080/api/v1/student/1

// PUT generated request
// PUT http://localhost:8080/api/v1/student/1?name=Maria&email=maria@gmail.com