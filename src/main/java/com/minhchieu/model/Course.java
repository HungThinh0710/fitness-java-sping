package com.minhchieu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;
    @Column
    private String type;
    @Column(name = "price_money")
    private float priceMoney;
    @Column
    private int status;

    @OneToMany(mappedBy = "coursePost", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (CoursePost) (1 course có nhiều Course Post)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<CoursePost> coursePosts;

    @OneToMany(mappedBy = "courseSchedule", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Schedule) (1 course có nhiều Schedule)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Schedule> schedules;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacherCourse;

    @ManyToMany(mappedBy = "courses")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Collection<Customer> customers;

}


