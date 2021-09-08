package com.minhchieu.serviceimpl;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.model.Account;
import com.minhchieu.model.Admin;
import com.minhchieu.model.Role;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
public class CustomAuthenticateService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(email);
        if(account == null)
            throw new UsernameNotFoundException("User not found with Email: " + email);
//        List<SimpleGrantedAuthority> roles = null;
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                // ROLE_USER, ROLE_ADMIN,..
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
        return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
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
    }
}
