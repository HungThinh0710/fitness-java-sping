package com.minhchieu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String job;
    private String description;
    private int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountCustomer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Quan hệ n-n với đối tượng ở dưới (Course) (1 customer có nhiều course)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "customers_courses", //Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "customer_id"),  // TRong đó, khóa ngoại chính là Customer_id trỏ tới class hiện tại (Customer)
            inverseJoinColumns = @JoinColumn(name = "course_id") //Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Course)
    )
    private Collection<Course> courses;
}
