package com.minhchieu.controller;

import com.minhchieu.mapstruct.dto.PostGetDTO;
import com.minhchieu.mapstruct.mapper.MapStructMapper;
import com.minhchieu.orm.CoursePostRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@RequestMapping("/public")
@RestController
@Tag(name = "Public API")
public class PublicController {

    @Autowired
    CoursePostRepository coursePostRepository;
    @Autowired
    MapStructMapper mapStructMapper;

    @GetMapping("/post")
    public ResponseEntity<?> allPost(){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "posts", coursePostRepository.findAll()
        ));
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> findPostbyId(@PathVariable(value = "id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "posts", coursePostRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        ));
    }

}
