package com.minhchieu.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "customer_courses")
public class CustomerCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "register_date")
    private Timestamp registerDate;
    private int status;
    private String note;
}
