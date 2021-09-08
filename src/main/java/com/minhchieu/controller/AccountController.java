package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.Account;
import com.minhchieu.model.Role;
import com.minhchieu.model.Subscription;
import com.minhchieu.orm.AccountRepository;
import com.minhchieu.orm.RoleRepository;
import com.minhchieu.orm.SubscriptionRepository;
import com.minhchieu.serviceimpl.CustomAuthenticateService;
import com.minhchieu.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private SubscriptionRepository subscriptionRepository;
    private MapStructMapper mapStructMapper;
    private CustomAuthenticateService customAuthenticateService;
    private JwtUtils jwtUtils;

    @Autowired
    public AccountController(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            SubscriptionRepository subscriptionRepository,
            MapStructMapper mapStructMapper,
            CustomAuthenticateService customAuthenticateService,
            JwtUtils jwtUtils
    ){
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.mapStructMapper = mapStructMapper;
        this.customAuthenticateService = customAuthenticateService;
        this.jwtUtils = jwtUtils;

    }

    @GetMapping("/")
    public List<Account> index(){
        return accountRepository.findAll();
    }

    @GetMapping("{/id}")
    public ResponseEntity<AccountGetDTO> getAccountById(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(
                mapStructMapper.accountToAccountGetDTO(
                        accountRepository.findById(id).get()
                ), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody AccountPostDTO user) throws Exception {
        Role role = roleRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException());
        Subscription subscription = subscriptionRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException());
        user.setRole(role);
        user.setSubscription(subscription);
        AccountGetDTO accountGetDTO = mapStructMapper.accountToAccountGetDTO(customAuthenticateService.save(user));
//        customAuthenticateService.save(user);
//        return accountGetDTO;
        return ResponseEntity.ok(accountGetDTO);
    }


}
