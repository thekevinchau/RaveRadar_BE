package com.project.RaveRadar.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 255)
    private String name;

    @Column(length = 255, unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_path", columnDefinition = "TEXT")
    private String avatarPath;

    @Column(length = 255)
    private String pronouns;
}