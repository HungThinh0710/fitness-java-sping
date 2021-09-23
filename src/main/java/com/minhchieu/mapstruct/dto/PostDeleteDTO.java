package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteDTO {
    @JsonProperty("post_id")
    private long postId;
}
