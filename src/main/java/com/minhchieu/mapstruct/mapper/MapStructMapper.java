package com.minhchieu.mapstruct.mapper;

import com.minhchieu.mapstruct.dto.*;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.model.CoursePost;
import com.minhchieu.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(
   componentModel = "spring"
)
@Component
public interface MapStructMapper {

    AccountGetDTO accountToAccountGetDTO(Account account);
    Account accountToAccountPostDTO(AccountPostDTO accountPostDTO);
    CourseGetDTO courseToCourseGetDTO(Course course);
    EnrolledCourseGetDTO enrolledCourseToCourseGetDTO(Customer customer);

    PostGetDTO postToPostGetDTO(CoursePost coursePost);
}
