package com.project.RaveRadar.payloads;

import lombok.Data;

@Data
public class UserProfileEdit {
    private String name;
    private String username;
    private String bio;
    private String avatarPath;
    private String pronouns;
}
