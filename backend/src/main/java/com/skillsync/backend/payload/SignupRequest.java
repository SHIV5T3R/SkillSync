package com.skillsync.backend.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    @Size(max = 39)
    private String username;

    @NotBlank
    @Size(max = 320)
    @Email
    private String email;

    @Schema(example = "ExamplePassword123!")
    @NotBlank
    @Size(min = 8, max = 100)
    @Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter")
    @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")
    @Pattern(regexp = ".*[()\\[\\]{}\"'\\-_.,;:!?`~|^@#$%&*+=].*", message = "Password must contain at least one special character !()[]{}\"'-_.,;:!?`~|^@#$%&*+=")
    @Pattern(regexp = "^[^<>]*$", message = "Password cannot contain < or > characters")
    private String password;

}