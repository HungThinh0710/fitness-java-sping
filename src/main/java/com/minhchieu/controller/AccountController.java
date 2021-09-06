package com.minhchieu.controller;

import com.minhchieu.model.Account;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public List<Account> index(){
        return accountRepository.findAll();
    }

    @PostMapping("/account/create")
    public Account create(@RequestBody Map<String, String> body) throws ParseException {

        Timestamp ts=new Timestamp(new java.util.Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date execDate = new Date(dateFormat.parse(body.get("dob")).getTime());

        Account account = new Account();
//        account.setName(body.get("name"));
//        account.setDob(execDate);
//        account.setEmail(body.get("email"));
//        account.setGender(body.get("gender"));
//        account.setPhone(body.get("phone"));
//        account.setType("");
//        account.setPassword("$2yexzy");
//        account.setAddress(body.get("address"));
//        account.setCreatedAt(ts);
//        account.setUpdatedAt(ts);
        return account;
    }

    @GetMapping("/hola")
    public String hello(){
        return "This is from Spring";
    }

//    @GetMapping("/teachers")
//    public Collection teachers() {
//        return accountRepository.
//    }
}
