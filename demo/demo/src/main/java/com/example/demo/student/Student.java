package com.example.demo.student;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity // for hibernate
@Table // as table
@NoArgsConstructor // create empty constructor
@AllArgsConstructor // create constructor for all columns
@Setter // create setter for all columns
@Getter // create getter for all columns
@ToString // create toString
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    // in Java 16 its fine without @Column,
    // since all var except @Transient will automatically become column.
    @Column
    private String name;

    @Column
    private String email;

    @Column
    private LocalDate dob;

    @Transient // not included as column
    private Integer age;


    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
