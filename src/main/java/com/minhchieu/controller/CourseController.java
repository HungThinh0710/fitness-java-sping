package com.minhchieu.controller;

import com.minhchieu.model.Account;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CourseController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/customer/enrolled")
    public ResponseEntity<?> enrolledCourse(Authentication authentication)
    {
        Account account = accountRepository.findByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "courses", account.getCustomer().getCourses()
        ));
    }
}
