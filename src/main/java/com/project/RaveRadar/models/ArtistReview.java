package com.project.RaveRadar.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist_reviews")
public class ArtistReview {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @OneToOne(optional = false)
    @JoinColumn(name = "reviewer_id", unique = true)
    private UserProfile reviewer;

    @OneToOne(optional = false)
    @JoinColumn(name = "artist_id", unique = true)
    private Artist artist;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_edited")
    private boolean isEdited = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    private Instant updatedAt;
}

