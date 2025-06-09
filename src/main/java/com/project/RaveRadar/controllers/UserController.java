package com.project.RaveRadar.controllers;

import com.project.RaveRadar.models.User;
import com.project.RaveRadar.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return userService.register(user);
    }
}
