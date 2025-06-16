package com.project.RaveRadar.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfileEdit {
    @Size(min = 1, max=255, message = "Name must be between 1 and 255 characters.")
    private String name;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]{3,20}$",
            message = "Username can only contain letters, numbers, underscores, hyphens, and periods"
    )
    private String username;
    private String bio;
    private String avatarPath;
    private String pronouns;
}
