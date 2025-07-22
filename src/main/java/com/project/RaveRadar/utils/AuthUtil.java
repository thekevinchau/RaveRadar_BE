package com.project.RaveRadar.utils;

import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@AllArgsConstructor
@Component
public class AuthUtil {
    private final UserRepository userRepository;
    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            throw new NotFoundException("No authenticated user found.");
        }
        Optional<User> userOptional = userRepository.findByEmail(auth.getName());
        if (userOptional.isEmpty()){
            throw new NotFoundException("User does not exist.");
        }
        return userOptional.get();
    }
    public void isUserAdmin(){
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals("ROLE_ADMIN")){
            throw new ForbiddenException("You are not authorized to make an announcement.");
        }
    }
}
