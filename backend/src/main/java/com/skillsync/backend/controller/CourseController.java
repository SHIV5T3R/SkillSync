package com.skillsync.backend.controller;

import com.skillsync.backend.model.Course;
import com.skillsync.backend.payload.CourseDTO;
import com.skillsync.backend.payload.CoursesResponse;
import com.skillsync.backend.payload.GenericResponse;
import com.skillsync.backend.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<CoursesResponse> getAllCourses() {
        log.info("GET request to get all courses.");
        return ResponseEntity.ok(
                new CoursesResponse(courseService.getAllCourses())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        log.info("GET request to get course by id {}", id);
        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    @GetMapping("/getByUnique")
    public ResponseEntity<Course> getCourseByTitle(@RequestParam("title") String title) {
        log.info("GET request to get course by title {}", title);
        return ResponseEntity.ok(
                courseService.getCourseByTitle(title)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        log.info("POST request to create course {}", courseDTO);

        Course course = new Course(courseDTO.getTitle(), courseDTO.getDescription());

        return new ResponseEntity<>(
                courseService.createCourse(course),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Course> updateCourse(@Valid @RequestBody CourseDTO courseDTO) {
        log.info("PUT request to update course {}", courseDTO);

        Course course = new Course(courseDTO.getTitle(), courseDTO.getDescription());
        course.setCourseId(courseDTO.getCourseId());

        return new ResponseEntity<>(
                courseService.updateCourse(course),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteCourseById(@PathVariable Long id) {
        log.info("DELETE request to delete course by id {}", id);
        courseService.deleteCourseById(id);
        return new ResponseEntity<>(
                new GenericResponse("Successfully deleted course by id " + id),
                HttpStatus.OK
        );
    }

}
