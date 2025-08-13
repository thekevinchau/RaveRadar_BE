package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserRegPayload {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&^+=])[A-Za-z\\d@$!%*#?&^+=]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]{3,20}$",
            message = "Username can only contain letters, numbers, underscores, hyphens, and periods"
    )
    private String displayName;

    @NotBlank( message = "Birthday is required")
    @NotNull
    private String birthday;

    private String phoneNumber;
}

