package com.skillsync.backend.service;

import com.skillsync.backend.exception.ResourceNotFoundException;
import com.skillsync.backend.model.Course;
import com.skillsync.backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private static final String RESOURCE_NAME = "Course";

    private final CourseRepository courseRepository;

    @Override
    public Collection<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id));
    }

    @Override
    public Course getCourseByTitle(String title) {
        return courseRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "title", title));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        if (!courseRepository.existsById(course.getCourseId())) {
            throw new ResourceNotFoundException(RESOURCE_NAME, "id", course.getCourseId());
        }
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException(RESOURCE_NAME, "id", id);
        }
        courseRepository.deleteById(id);
    }

}
