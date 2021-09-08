package com.minhchieu.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String note;
    @Column(name = "teach_time")
    private Timestamp teachTime;
    private float duration;
    private int attendance;
    @Column(name = "attendance_time")
    private Timestamp attendanceTime;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    // Many to One có nhiều schedule trong 1 course
    @ManyToOne
    @JoinColumn(name = "course_id") // thông qua khóa ngoại Course_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course courseSchedule;
}
