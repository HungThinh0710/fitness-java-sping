package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

@Getter
@Setter
public class SelectAccountTypePostDTO {
    @JsonProperty("account_type")
    @NotNull(message = "Account Type is required")
    @Min(value=1, message="must be equal or greater than 1")
    @Max(value=2, message="must be equal or less than 2")
    private int accountType;
}
