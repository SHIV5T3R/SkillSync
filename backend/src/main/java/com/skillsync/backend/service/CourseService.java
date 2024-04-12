package com.skillsync.backend.service;

import com.skillsync.backend.model.Course;

import java.util.Collection;

public interface CourseService {

    Collection<Course> getAllCourses();
    Course getCourseById(Long id);
    Course getCourseByTitle(String title);
    Course createCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourseById(Long id);

}
