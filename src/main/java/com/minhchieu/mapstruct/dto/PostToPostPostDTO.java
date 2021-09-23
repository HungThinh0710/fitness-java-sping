package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class PostToPostPostDTO {
    @JsonProperty("course_id")
    @NotNull
//    @NumberFormat()
    @Min(value=1, message="must be equal or greater than 10000")
    @Max(value=999999999, message="must be equal or less than 999999999")
    private int courseId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("content")
    @NotNull(message = "content is required")
    private String content;

    @JsonProperty("status")
    @NotNull(message = "status is required")
    private int status;




}
