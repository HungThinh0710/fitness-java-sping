package com.minhchieu.validator;

import com.minhchieu.annotation.UniqueEmail;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.orm.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //IF show error: query did not return a unique result: 14; nested exception is javax.persistence.NonUniqueResultException: query did not return a unique result: 14
        //cause: Same duplicate/mutiple value in email field database.
        return accountRepository.findByEmail(email) == null;

    }

}
