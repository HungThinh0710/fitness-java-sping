package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class AuthenticateRequestDTO {

    @NotBlank(message = "Email is required!")
    @NotNull(message = "Email is required!")
    @JsonProperty("email")
    private String email;
    @NotBlank(message = "Password is required!")
    @NotNull(message = "Password is required!")
    @JsonProperty("password")
    private String password;

    public AuthenticateRequestDTO(){}

    public AuthenticateRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
