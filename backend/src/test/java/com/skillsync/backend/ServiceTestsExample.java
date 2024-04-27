package com.skillsync.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceTestsExample {

    /*
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
    */

    // ... other tests

}
