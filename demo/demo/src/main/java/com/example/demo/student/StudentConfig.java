package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {

    @Bean // to run the command line runner
    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ) {
        return args -> {
            // seeding
            // ctrl + alt + v --> introduce variable
            Student mariam = new Student(
                    null,
                    "Mariam",
                    "mariam.jama@gmail.com",
                    LocalDate.of(2000, JANUARY, 5),
                    null
            );

            Student alex = new Student(
                    null,
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, JANUARY, 5),
                    null
            );

            // save to database
            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}
