package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.mapstruct.dto.SelectAccountTypePostDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.*;
import com.minhchieu.orm.*;
import com.minhchieu.serviceimpl.CustomAuthenticateService;
import com.minhchieu.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private SubscriptionRepository subscriptionRepository;
    private TeacherRepository teacherRepository;
    private CustomerRepository customerRepository;
    private MapStructMapper mapStructMapper;
    private CustomAuthenticateService customAuthenticateService;
    private JwtUtils jwtUtils;

    @Autowired
    public AccountController(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            TeacherRepository teacherRepository,
            CustomerRepository customerRepository,
            SubscriptionRepository subscriptionRepository,
            MapStructMapper mapStructMapper,
            CustomAuthenticateService customAuthenticateService,
            JwtUtils jwtUtils
    ){
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.teacherRepository = teacherRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.customerRepository = customerRepository;
        this.mapStructMapper = mapStructMapper;
        this.customAuthenticateService = customAuthenticateService;
        this.jwtUtils = jwtUtils;

    }

//    @GetMapping("/")
//    public List<Account> index(){
//        return accountRepository.findAll();
//    }

    @GetMapping("/")
    public ResponseEntity<AccountGetDTO> getCurrentAccount(Authentication authentication){
        String email = authentication.getName();
        AccountGetDTO accountGetDTO = mapStructMapper.accountToAccountGetDTO(accountRepository.findByEmail(email));
        return new ResponseEntity<>(accountGetDTO, HttpStatus.OK);
    }

    @GetMapping("{/id}")
    public ResponseEntity<AccountGetDTO> getAccountById(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(
                mapStructMapper.accountToAccountGetDTO(
                        accountRepository.findById(id).get()
                ), HttpStatus.OK);
    }

    @PostMapping("/select-type")
    public ResponseEntity<?> selectAccountType(Authentication authentication, @Valid @RequestBody SelectAccountTypePostDTO request){
        Account account = accountRepository.findByEmail(authentication.getName());
        if(account.getTeacher() == null && account.getCustomer() == null){
            switch (request.getAccountType()){
                case 1:
                    Teacher teacher = new Teacher();
                    teacher.setDescription("");
                    teacher.setStatus(0);
                    teacher.setAccountTeacher(account);
                    account.setTeacher(teacher);
                    teacherRepository.save(teacher);
                    break;
                case 2:
                    Customer customer = new Customer();
                    customer.setJob("");
                    customer.setDescription("");
                    customer.setStatus(0);
                    customer.setAccountCustomer(account);
                    account.setCustomer(customer);
                    customerRepository.save(customer);
                    break;
            }
            String accountTypeText = request.getAccountType() == 1 ? "teacher": "customer";
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message","Select account type to " + accountTypeText + " successfully!"
            ));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message","This account is already select type."
        ));
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
