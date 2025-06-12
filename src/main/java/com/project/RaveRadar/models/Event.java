package com.project.RaveRadar.models;

import com.project.RaveRadar.enums.AgeRestriction;
import com.project.RaveRadar.enums.EdmGenre;
import com.project.RaveRadar.enums.EventType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "event_id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID eventId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", length = 255)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", length = 255)
    private EdmGenre genre;

    @Column(name = "external_link", columnDefinition = "text")
    private String externalLink;

    @Column(name = "image_path", columnDefinition = "text")
    private String imagePath;

    @Column(name = "support_email")
    private String supportEmail;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction", length = 255)
    private AgeRestriction ageRestriction;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;
}
