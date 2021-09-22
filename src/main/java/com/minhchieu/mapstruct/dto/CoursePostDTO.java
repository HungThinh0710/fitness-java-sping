package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CoursePostDTO {

    @JsonProperty("name")
    @NotNull
    private String name;

    @Min(value=10000, message="must be equal or greater than 10000")
    @Max(value=999999999, message="must be equal or less than 999999999")
    @JsonProperty("price_money")
    private float priceMoney;
}
