package com.minhchieu.mapstruct.mapper;

import com.minhchieu.mapstruct.dto.AccountGetDTO;
import com.minhchieu.mapstruct.dto.AccountPostDTO;
import com.minhchieu.mapstruct.dto.CourseGetDTO;
import com.minhchieu.mapstruct.dto.EnrolledCourseGetDTO;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.model.Customer;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-20T18:12:06+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public AccountGetDTO accountToAccountGetDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountGetDTO accountGetDTO = new AccountGetDTO();

        if ( account.getId() != null ) {
            accountGetDTO.setId( account.getId().intValue() );
        }
        accountGetDTO.setName( account.getName() );
        accountGetDTO.setDob( account.getDob() );
        accountGetDTO.setGender( account.getGender() );
        accountGetDTO.setPhone( account.getPhone() );
        accountGetDTO.setType( account.getType() );
        accountGetDTO.setAddress( account.getAddress() );
        accountGetDTO.setEmail( account.getEmail() );
        accountGetDTO.setRole( account.getRole() );
        accountGetDTO.setSubscription( account.getSubscription() );
        accountGetDTO.setTeacher( account.getTeacher() );
        accountGetDTO.setCustomer( account.getCustomer() );

        return accountGetDTO;
    }

    @Override
    public Account accountToAccountPostDTO(AccountPostDTO accountPostDTO) {
        if ( accountPostDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountPostDTO.getId() );
        account.setName( accountPostDTO.getName() );
        account.setDob( accountPostDTO.getDob() );
        account.setEmail( accountPostDTO.getEmail() );
        account.setGender( accountPostDTO.getGender() );
        account.setPhone( accountPostDTO.getPhone() );
        account.setType( accountPostDTO.getType() );
        account.setPassword( accountPostDTO.getPassword() );
        account.setAddress( accountPostDTO.getAddress() );
        account.setCreatedAt( accountPostDTO.getCreatedAt() );
        account.setUpdatedAt( accountPostDTO.getUpdatedAt() );
        account.setRole( accountPostDTO.getRole() );
        account.setSubscription( accountPostDTO.getSubscription() );

        return account;
    }

    @Override
    public CourseGetDTO courseToCourseGetDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseGetDTO courseGetDTO = new CourseGetDTO();

        courseGetDTO.setId( (int) course.getId() );
        courseGetDTO.setPriceMoney( course.getPriceMoney() );
        courseGetDTO.setName( course.getName() );

        return courseGetDTO;
    }

    @Override
    public EnrolledCourseGetDTO enrolledCourseToCourseGetDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        EnrolledCourseGetDTO enrolledCourseGetDTO = new EnrolledCourseGetDTO();

        Collection<Course> collection = customer.getCourses();
        if ( collection != null ) {
            enrolledCourseGetDTO.setCourses( new ArrayList<Course>( collection ) );
        }

        return enrolledCourseGetDTO;
    }
}
