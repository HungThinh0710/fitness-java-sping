package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minhchieu.annotation.UniqueEmail;
import com.minhchieu.model.Role;
import com.minhchieu.model.Subscription;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Data
public class AccountPostDTO {
    @JsonProperty("id")
    private Long id;

//    @NotNull
    @JsonProperty("role")
    private Role role;

//    @NotNull
    @JsonProperty("subscription")
    private Subscription subscription;

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("dob")
    private Date dob;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("type")
    private String type ;

    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private String address ;

    @Email(message = "Your email address is not valid")
//    @NotNull
    @UniqueEmail
    @JsonProperty("email")
    private String email;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;


}
