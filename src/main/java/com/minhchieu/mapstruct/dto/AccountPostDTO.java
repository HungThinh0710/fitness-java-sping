package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minhchieu.model.Role;
import com.minhchieu.model.Subscription;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Source;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class AccountPostDTO {
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("role")
    private Role role;

    @NotNull
    @JsonProperty("subscription")
    private Subscription subscription;

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

    @NotNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private String address ;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @NotNull
    @JsonProperty("updated_at")
    private Timestamp updatedAt;


}
