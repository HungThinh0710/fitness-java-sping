package com.minhchieu.mapstruct.mapper;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.mapstruct.dto.EnrolledCourseGetDTO;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Mapper(
   componentModel = "spring"
)
@Component
public interface MapStructMapper {

    AccountGetDTO accountToAccountGetDTO(Account account);
    Account accountToAccountPostDTO(AccountPostDTO accountPostDTO);

    EnrolledCourseGetDTO enrolledCourseToCourseGetDTO(Customer customer);
}
