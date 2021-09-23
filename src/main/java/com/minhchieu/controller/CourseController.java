package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.CourseGetByIdDTO;
import com.minhchieu.mapstruct.dto.CourseGetDTO;
import com.minhchieu.mapstruct.dto.CoursePostDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.Account;
import com.minhchieu.model.Course;
import com.minhchieu.model.Teacher;
import com.minhchieu.orm.AccountRepository;
import com.minhchieu.orm.CourseRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Tag(name = "Course API")
@SecurityRequirement(name = "api")

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

    @PostMapping("/course")
    public ResponseEntity<?> create(@Valid @RequestBody CoursePostDTO request, Authentication authentication){
        //Default value
        int status = 0; //pending
        String type = "";
        Course course = new Course();
        course.setName(request.getName());
        course.setPriceMoney(request.getPriceMoney());
        course.setStatus(status);
        course.setType(type);
        course.setTeacherCourse(accountRepository.findByEmail(authentication.getName()).getTeacher());
        courseRepository.save(course);
        CourseGetDTO courseGetDTO = mapStructMapper.courseToCourseGetDTO(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "message", "Created course successfully, but this course need approve from administrator",
            "status", "pending",
            "course",courseGetDTO

        ));
    }

    @PutMapping("/course")
    //WITHOUT VALIDATE OWN COURSE
    public ResponseEntity<?> create(@RequestBody CoursePostDTO request){
        Course course = courseRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        course.setName(request.getName());
        course.setPriceMoney(request.getPriceMoney());
        courseRepository.save(course);
        CourseGetDTO courseGetDTO = mapStructMapper.courseToCourseGetDTO(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "message", "Edit course Successfully",
            "course",courseGetDTO
        ));
    }
}
