package com.project.RaveRadar.utils;

import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public boolean isUserAdmin(){
        User currentUser = getCurrentUser();
        if (!currentUser.getRole().equals("ROLE_ADMIN")){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean isUserAdmin(Authentication authentication) {
        // The principal contains the authenticated user
        if (authentication == null){
            throw new ForbiddenException("You are not currently logged in!");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        // Get roles as strings
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        System.out.println(roles);
        return roles.contains("ROLE_ADMIN");
    }
}
