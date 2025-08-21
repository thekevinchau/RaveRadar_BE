package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.exceptions.ResourceAlreadyExistsException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.UserRegPayload;
import com.project.RaveRadar.repositories.UserRepository;
import com.project.RaveRadar.security.JwtUtil;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager manager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthUtil authUtil;

    private final UserRepository userRepository;
    private final UserProfileService profileService;

    public UserService(PasswordEncoder passwordEncoder, AuthenticationManager manager, UserRepository userRepository, UserProfileService profileService) {
        this.passwordEncoder = passwordEncoder;
        this.manager = manager;
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    @Transactional
    public ResponseEntity<UserProfileDTO> register(UserRegPayload payload){
        Optional<User> userOptional = userRepository.findByEmail(payload.getEmail());
        if (userOptional.isEmpty()){
            User newUser = new User();
            newUser.setEmail(payload.getEmail());
            String currentPassword = payload.getPassword();
            newUser.setPassword(passwordEncoder.encode(currentPassword));
            newUser.setRole("ROLE_USER");
            User savedUser = userRepository.save(newUser);
            UserProfile newProfile = profileService.createUserProfile(savedUser, payload.getDisplayName(), LocalDate.parse(payload.getBirthday()), payload.getPhoneNumber());
            return ResponseEntity.ok(new UserProfileDTO(newProfile));
        }
        throw new ResourceAlreadyExistsException("A user with that email already exists!");
    }

    public ResponseEntity<UserProfileDTO> cookieLogin(User user, HttpServletResponse response){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        );

        Authentication authentication = manager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());
        ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                .httpOnly(true)
                .secure(false) // true if on HTTPS in prod
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        UserProfileDTO dto = profileService.getMyProfile().getBody();
        assert dto != null;
        dto.setAdmin(authUtil.isUserAdmin(authentication));
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<UserProfileDTO> getMyProfile(){
        UserProfileDTO dto = profileService.getMyProfile().getBody();
        assert dto != null;
        dto.setAdmin(authUtil.isUserAdmin());
        return ResponseEntity.ok(dto);
    }
}