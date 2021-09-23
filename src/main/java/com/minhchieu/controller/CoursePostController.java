package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.PostGetDTO;
import com.minhchieu.mapstruct.dto.PostToPostPostDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.model.Course;
import com.minhchieu.model.CoursePost;
import com.minhchieu.orm.CoursePostRepository;
import com.minhchieu.orm.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

@RequestMapping("/post")
@RestController
public class CoursePostController {

    @Autowired
    CoursePostRepository coursePostRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MapStructMapper mapStructMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody PostToPostPostDTO post) {


        CoursePost coursePost = new CoursePost();
        Course course = courseRepository.findById((long) post.getCourseId()).orElseThrow(EntityNotFoundException::new);
        coursePost.setCourse(course);
        coursePost.setDescription(post.getDescription());
        coursePost.setContent(post.getContent());
        coursePost.setStatus(post.getStatus());
        coursePost.setCreated_at(Timestamp.from(Instant.now()));
        coursePost.setUpdated_at(Timestamp.from(Instant.now()));
        PostGetDTO postGetDTO = mapStructMapper.postToPostGetDTO(coursePostRepository.save(coursePost));
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "message","Create post successfully",
            "post", postGetDTO
        ));
    }
}
