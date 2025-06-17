package com.project.RaveRadar.services;

import com.project.RaveRadar.DTO.UserProfileDTO;
import com.project.RaveRadar.config.S3Config;
import com.project.RaveRadar.exceptions.ForbiddenException;
import com.project.RaveRadar.exceptions.NotFoundException;
import com.project.RaveRadar.models.User;
import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.payloads.UserProfileEdit;
import com.project.RaveRadar.repositories.UserProfileRepository;
import com.project.RaveRadar.repositories.UserRepository;
import com.project.RaveRadar.utils.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserProfileService {
    private final UserProfileRepository profileRepository;
    private final AuthUtil util;
    private final S3Config s3Config;
    private final String bucketName;

    public UserProfileService(UserProfileRepository profileRepository, AuthUtil util, S3Config s3Config, @Value("${aws.s3.bucket-name}")String bucketName) {
        this.profileRepository = profileRepository;
        this.util = util;
        this.s3Config = s3Config;
        this.bucketName = bucketName;
    }
    //only need two fields when first creating the user profile

    public ResponseEntity<UserProfileDTO> getProfileByUsername(String username){
        Optional<UserProfile> profileOptional = profileRepository.findByUsername(username);
        if (profileOptional.isEmpty()){
            throw new NotFoundException("User profile was not found.");
        }
        UserProfile profile = profileOptional.get();
        return ResponseEntity.ok(new UserProfileDTO(profile));
    }

    public ResponseEntity<UserProfileDTO> getProfileById(UUID id){
        Optional<UserProfile> profileOptional = profileRepository.findById(id);
        if (profileOptional.isEmpty()){
            throw new NotFoundException("User profile was not found.");
        }
        UserProfile profile = profileOptional.get();
        return ResponseEntity.ok(new UserProfileDTO(profile));
    }

    public ResponseEntity<UserProfileDTO> getPrincipalProfile(){
        Optional<UserProfile> profileOptional = profileRepository.findByUser(util.getCurrentUser());
        if (profileOptional.isEmpty()){
            throw new NotFoundException("User profile was not found.");
        }
        UserProfile profile = profileOptional.get();
        return ResponseEntity.ok(new UserProfileDTO(profile));
    }

    @Transactional
    public void createUserProfile(User user, String username){
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setUsername(username);
        profileRepository.save(profile);
    }


    private String generateAvatarUploadPresignedUrl(String filePath) {
        //parse the file type from the file path -> ex: .jpg, .jpeg, .png, .gif, etc.
        String fileExtension = "." + filePath.split("\\.")[1];

        PutObjectRequest.Builder putObjectRequestBuilder = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath);

        PutObjectRequest putObjectRequest = putObjectRequestBuilder.build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(60))
                .putObjectRequest(putObjectRequest)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Config.s3Presigner().presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }


    @Transactional
    public ResponseEntity<UserProfileDTO> editUserProfile(UserProfileEdit edit){
        User currentUser = util.getCurrentUser();
        Optional<UserProfile> profileOptional = profileRepository.findByUser(currentUser);
        if (profileOptional.isEmpty()){
            throw new NotFoundException("User profile was not found");
        }
        UserProfile profile = profileOptional.get();

        if (!profile.getUser().getId().equals(currentUser.getId())){
            throw new ForbiddenException("You do not have access to this resource.");
        }
        profile.setUsername(edit.getUsername());
        profile.setName(edit.getName());
        profile.setBio(edit.getBio());
        profile.setAvatarPath(edit.getAvatarPath());
        profile.setPronouns(edit.getPronouns());
        return ResponseEntity.ok(new UserProfileDTO(profileRepository.save(profile)));
    }
}
