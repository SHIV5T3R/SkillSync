package com.skillsync.backend;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.mockito.ArgumentMatchers.any;

// @WebMvcTest(controllers = CourseController.class)
// addFilters = false was added since the tests weren't able to bypass security even though at that point all endpoints were allowed
@AutoConfigureMockMvc(addFilters = false)
class ControllerTestsExample {

    /*
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
    */

    // ... other tests

    // (controllers can also be unit tested)

}
