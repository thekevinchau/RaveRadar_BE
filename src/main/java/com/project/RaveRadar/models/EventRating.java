package com.project.RaveRadar.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_rating", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_profile_id", "event_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRating {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "rating_sound_quality")
    private Integer ratingSoundQuality;

    @Column(name = "rating_bathrooms")
    private Integer ratingBathrooms;

    @Column(name = "rating_crowd_vibes")
    private Integer ratingCrowdVibes;

    @Column(name = "rating_venue")
    private Integer ratingVenue;

    @Column(name = "rating_overall")
    private Integer ratingOverall;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
