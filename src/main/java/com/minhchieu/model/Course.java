package com.minhchieu.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String type;
    private float price_money;
    private int status;

    @OneToMany(mappedBy = "coursePost", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (CoursePost) (1 course có nhiều Course Post)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CoursePost> coursePosts;

    @OneToMany(mappedBy = "courseSchedule", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Schedule) (1 course có nhiều Schedule)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacherCourse;

    @ManyToMany(mappedBy = "courses")
    @EqualsAndHashCode.Exclude
    private Collection<Customer> customers;

}
