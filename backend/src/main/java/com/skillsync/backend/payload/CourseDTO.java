package com.skillsync.backend.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseDTO {

    private Long courseId;

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;

    @Size(max = 1023, message = "Description must be less than 1023 characters")
    private String description;

    public CourseDTO(String title, String description) {
        setTitle(title);
        setDescription(description);
    }

}
