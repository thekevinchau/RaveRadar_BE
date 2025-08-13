package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.ArtistReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistReviewRepository extends JpaRepository<ArtistReview, UUID> {
}
