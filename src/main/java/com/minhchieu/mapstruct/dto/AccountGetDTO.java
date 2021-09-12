package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minhchieu.model.Customer;
import com.minhchieu.model.Role;
import com.minhchieu.model.Subscription;
import com.minhchieu.model.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AccountGetDTO {
    @JsonProperty("id")
    private int id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("dob")
    private Date dob;

    @NotNull
    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("type")
    private String type ;

    @JsonProperty("address")
    private String address ;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("role")
    private Role role;

    @NotNull
    @JsonProperty("subscription")
    private Subscription subscription;

    @JsonProperty("teacher")
    private Teacher teacher;

    @JsonProperty("customer")
    private Customer customer;

    public AccountGetDTO(){}

    public AccountGetDTO(int id, String name, Date dob, String gender, String phone, String type, String address, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.type = type;
        this.address = address;
        this.email = email;
    }
}
