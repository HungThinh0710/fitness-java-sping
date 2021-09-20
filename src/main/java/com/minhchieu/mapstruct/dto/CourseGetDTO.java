package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class CourseGetDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("priceMoney")
    private float priceMoney;

    @JsonProperty("name")
    private String name;


}
