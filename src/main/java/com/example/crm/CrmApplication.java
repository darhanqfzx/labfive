package com.example.crm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.crm.model.Courses;
import com.example.crm.model.Operators;
import com.example.crm.model.ApplicationRequest;
import com.example.crm.repository.CoursesRepository;
import com.example.crm.repository.OperatorsRepository;
import com.example.crm.repository.ApplicationRequestRepository;

import java.util.List;

@SpringBootApplication
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(CoursesRepository coursesRepo, OperatorsRepository opRepo, ApplicationRequestRepository reqRepo) {
        return args -> {
            // preload courses
            Courses c1 = new Courses(null, "Java Basic", "Intro to Java", 100);
            Courses c2 = new Courses(null, "Spring Boot", "Build web apps", 150);
            Courses c3 = new Courses(null, "Python", "Python for beginners", 120);
            coursesRepo.saveAll(List.of(c1, c2, c3));

            // preload operators
            Operators o1 = new Operators(null, "Ali", "K.", "IT");
            Operators o2 = new Operators(null, "Dana", "M.", "Marketing");
            Operators o3 = new Operators(null, "Erik", "S.", "Support");
            opRepo.saveAll(List.of(o1, o2, o3));

            // preload sample requests
            ApplicationRequest r1 = new ApplicationRequest(null, "Zharaskan", "Need info", "+77010000000", false, c1);
            ApplicationRequest r2 = new ApplicationRequest(null, "Aida", "Please call", "+77019999999", false, c2);
            reqRepo.saveAll(List.of(r1, r2));
        };
    }
}
