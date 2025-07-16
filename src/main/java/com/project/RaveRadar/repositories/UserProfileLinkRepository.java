package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.UserProfile;
import com.project.RaveRadar.models.UserProfileLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileLinkRepository extends JpaRepository<UserProfileLink, UUID> {
    Optional<UserProfileLink> findByUserProfileAndPlatform(UserProfile userProfile, UserProfileLink.Platform platform);
    List<UserProfileLink> findByUserProfile(UserProfile profile);

}
