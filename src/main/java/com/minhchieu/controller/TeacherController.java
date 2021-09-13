package com.minhchieu.controller;

import com.minhchieu.model.Account;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/courses")
    public String listCourse(Authentication authentication){
        Account account = accountRepository.findByEmail(authentication.getName());
        return "Ok_you are passed because you are a teacher in this course";
    }
}
