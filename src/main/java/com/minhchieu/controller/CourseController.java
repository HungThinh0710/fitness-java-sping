package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.CourseGetByIdDTO;
import com.minhchieu.mapstruct.dto.CourseGetDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.orm.AccountRepository;
import com.minhchieu.orm.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class CourseController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    MapStructMapper mapStructMapper;

    @GetMapping("/customer/enrolled")
    public ResponseEntity<?> enrolledCourse(Authentication authentication) {
        Account account = accountRepository.findByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "courses", account.getCustomer().getCourses()
        ));
    }

    @GetMapping("/courses")
    public ResponseEntity<?> course() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "courses", courseRepository.findAll()
        ));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CourseGetDTO> courseById (@PathVariable(value = "id") Long id){
        CourseGetDTO course = mapStructMapper.courseToCourseGetDTO(courseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException()));
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
}
