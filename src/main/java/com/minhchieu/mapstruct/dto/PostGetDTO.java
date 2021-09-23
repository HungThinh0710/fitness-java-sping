package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PostGetDTO {
    @JsonProperty("id")
    private long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("content")
    private String content;
    @JsonProperty("status")
    private int status;
    @JsonProperty("created_at")
    private Timestamp created_at;
    @JsonProperty("updated_at")
    private Timestamp updated_at;
}
