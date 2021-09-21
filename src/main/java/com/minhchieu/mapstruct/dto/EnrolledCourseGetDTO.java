package com.minhchieu.mapstruct.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minhchieu.model.Course;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Data
public class EnrolledCourseGetDTO {
    @JsonProperty("enrolled_course")
    private Collection<Course> courses;
}
