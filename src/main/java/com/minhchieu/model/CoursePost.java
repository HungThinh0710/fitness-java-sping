package com.minhchieu.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "course_posts")
public class CoursePost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private String content;
    private int status;
    private Timestamp created_at;
    private Timestamp updated_at;

    // Many to One có nhiều post trong 1 course
    @ManyToOne
    @JoinColumn(name = "course_id") // thông qua khóa ngoại address_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course coursePost;
}
