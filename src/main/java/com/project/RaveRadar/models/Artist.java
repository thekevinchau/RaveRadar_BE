package com.project.RaveRadar.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "stage_name", nullable = false)
    private String stageName;

    @Column(name = "avatar_path")
    private String avatarPath;

    @Column(name = "banner_path")
    private String bannerPath;

    @Column(name = "musicbrainz_id", unique = true)
    private UUID musicbrainzId;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
}
