package com.skillsync.backend.controller;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
// For the security scheme, there is probably a way to do this "application-wide",
// so this does not have to be defined in every controller... not sure, though
// (... this is just for Swagger UI/OpenAPI)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class ProtectedController {

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> getProtectedSecured() {
        return ResponseEntity.ok("You are authenticated. Good job!");
    }

}
