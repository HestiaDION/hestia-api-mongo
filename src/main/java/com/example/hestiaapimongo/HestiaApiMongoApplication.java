package com.example.hestiaapimongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.hestiaapimongo.repository")
public class HestiaApiMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HestiaApiMongoApplication.class, args);
    }

}
