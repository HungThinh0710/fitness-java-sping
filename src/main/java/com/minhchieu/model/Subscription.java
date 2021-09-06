package com.minhchieu.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(name = "duration_month")
    private int durationMonth;
    private String description;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
    private Collection<Account> accounts;
}
