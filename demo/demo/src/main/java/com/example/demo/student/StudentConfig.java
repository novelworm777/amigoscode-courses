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
            // create data
            // ctrl + alt + v --> introduce variable
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jama@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, JANUARY, 5)
            );

            // save to database
            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}
