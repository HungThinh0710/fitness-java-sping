package com.minhchieu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.minhchieu.controller", "com.minhchieu.model", "com.minhchieu.orm"})
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        System.out.println("Server started! Enjoy at http://127.0.0.1:8080");
    }
}
