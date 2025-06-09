package com.project.RaveRadar.payloads;

import lombok.Data;

@Data
public class UserRegPayload {
    private String email;
    private String password;
    private String username;
}
