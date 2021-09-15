package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

// Service layer mainly for business logic

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        // ctrl + f6 --> change all variable with the same name
        // check email
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());

        // validation
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        // save new student to database
        studentRepository.save(student);

        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        // validation
        if (!exists) {
            throw new IllegalStateException(
                    "Student with id " + studentId + " does not exist"
            );
        }

        // delete by id
        studentRepository.deleteById(studentId);
    }

    @Transactional // if one of queries failed, rollback
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist"
                ));

        if (name != null
                && name.length() > 0 &&
                !Objects.equals(student.getName(), name)) {
            // update name
            student.setName(name);
        }

        if (email != null
                && email.length() > 0 &&
                !Objects.equals(student.getEmail(), email)) {
            // check email
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);

            // validation
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }

            // update email
            student.setEmail(email);
        }
    }
}
