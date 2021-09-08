package com.minhchieu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "dob", columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column
    private String email;
    @Column
    private String gender;
    @Column
    private String phone;
    @Column
    private String type;
    @Column
    private String password;
    @Column
    private String address;

    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToOne(mappedBy = "accountTeacher", fetch = FetchType.LAZY)
    private Teacher teacher;

    @OneToOne(mappedBy = "accountCustomer", fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    @JsonIgnore
//    @JsonBackReference
//    @JsonIdentityInfo(generator = "")
    private Role role;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "subscription_id")
//    @JsonBackReference
    private Subscription subscription;

    public Account(){}

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

        public Account(String name, Date dob, String email, String gender, String phone, String type, String password, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.type = type;
        this.password = password;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
