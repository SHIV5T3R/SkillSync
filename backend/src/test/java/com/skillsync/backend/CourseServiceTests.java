package com.skillsync.backend;

import com.skillsync.backend.model.Course;
import com.skillsync.backend.repository.CourseRepository;
import com.skillsync.backend.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(List.of(
                new Course("Java", "Java Programming"),
                new Course("Python", "Python Programming")
        ));

        Iterable<Course> courses = courseService.getAllCourses();

        assertNotNull(courses);
        assertEquals(2, ((List<Course>) courses).size());
    }

    @Test
    void testCreateCourse() {
        Course newCourse = new Course("title", "description");
        when(courseRepository.save(any())).thenReturn(newCourse);

        Course createdCourse = courseService.createCourse(newCourse);

        assertNotNull(createdCourse);
        assertEquals("title", createdCourse.getTitle());
    }

    // ... other tests

}
