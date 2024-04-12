package com.skillsync.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillsync.backend.controller.CourseController;
import com.skillsync.backend.model.Course;
import com.skillsync.backend.payload.CourseDTO;
import com.skillsync.backend.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CourseController.class)
@AutoConfigureMockMvc(addFilters = false)
class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCoursesEndpoint() throws Exception {
        List<Course> courses = List.of(
                new Course(1L, "title1", "description1"),
                new Course(2L, "title2", "description2")
        );
        when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courses.length()").value(2));
    }

    @Test
    void testCreateCourseEndpoint() throws Exception {
        CourseDTO courseDTO = new CourseDTO("title", "description");
        Course createdCourse = new Course(1L, "title", "description");
        when(courseService.createCourse(any())).thenReturn(createdCourse);

        mockMvc.perform(MockMvcRequestBuilders.post("/course/create")
                        .content(asJsonString(courseDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courseId").value("1"));
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // ... other tests

    // (controllers can also be unit tested)

}
