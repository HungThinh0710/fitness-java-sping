package com.minhchieu.service;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.model.Account;
import com.minhchieu.model.Admin;
import com.minhchieu.model.Role;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CustomAuthenticateService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<SimpleGrantedAuthority> roles = null;
        if(username.equals("admin"))
        {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return (UserDetails) new Admin("admin", "$2y$12$I0Di/vfUL6nqwVbrvItFVOXA1L9OW9kLwe.1qDPhFzIJBpWl76PAe");
        }
        else if(username.equals("user"))
        {
//            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            return (UserDetails) new Account("email", "$2y$12$VfZTUu/Yl5v7dAmfuxWU8uRfBKExHBWT1Iqi.s33727NoxHrbZ/h2");
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }


    public Account save(AccountPostDTO account){
        Timestamp ts = new Timestamp(new Date().getTime());
        Account accountEntity = new Account();
        accountEntity.setRole(account.getRole());
        accountEntity.setSubscription(account.getSubscription());
        accountEntity.setName(account.getName());
        accountEntity.setDob(account.getDob());
        accountEntity.setEmail(account.getEmail());
        accountEntity.setGender(account.getGender());
        accountEntity.setPhone(account.getPhone());
        accountEntity.setType(account.getType());
        accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        accountEntity.setAddress(account.getAddress());
        accountEntity.setCreatedAt(ts);
        accountEntity.setUpdatedAt(ts);
        accountRepository.save(accountEntity);
        return accountEntity;
//        AccountGetDTO accountGetDTO = new AccountGetDTO(accountEntity);
    }
}
