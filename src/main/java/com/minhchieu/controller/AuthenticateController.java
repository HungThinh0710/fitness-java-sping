package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.Role;
import com.minhchieu.model.Subscription;
import com.minhchieu.orm.AccountRepository;
import com.minhchieu.orm.RoleRepository;
import com.minhchieu.orm.SubscriptionRepository;
import com.minhchieu.service.CustomAuthenticateService;
import com.minhchieu.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/authenticate")
@RestController
public class AuthenticateController {
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private SubscriptionRepository subscriptionRepository;
    private MapStructMapper mapStructMapper;
    private CustomAuthenticateService customAuthenticateService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthenticateController(
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
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody AccountPostDTO user) throws Exception {
        Role role = roleRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException());
        Subscription subscription = subscriptionRepository.findById(1L).orElseThrow(()-> new EntityNotFoundException());
        user.setRole(role);
        user.setSubscription(subscription);
        AccountGetDTO accountGetDTO = mapStructMapper.accountToAccountGetDTO(customAuthenticateService.save(user));
        return ResponseEntity.status(HttpStatus.CREATED ).body(Map.of(
            "message","Register successfully",
            "account", accountGetDTO
        ));
    }
}
