package com.minhchieu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "schedules")
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
    @JsonIgnore
    private Course courseSchedule;
}
