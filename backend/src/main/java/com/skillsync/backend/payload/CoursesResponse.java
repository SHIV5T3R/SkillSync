package com.skillsync.backend.payload;

import com.skillsync.backend.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class CoursesResponse {

    private Collection<Course> courses;

}
