package com.project.RaveRadar.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile_links")
public class UserProfileLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", length = 100)
    private Platform platform;

    @Column(name = "external_link", columnDefinition = "TEXT")
    private String externalLink;

    public enum Platform {
        Spotify, Instagram, Twitter, YouTube
    }
}
