package com.minhchieu.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountTeacher;

    @OneToMany(mappedBy = "teacherCourse")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Course> courses;
}
