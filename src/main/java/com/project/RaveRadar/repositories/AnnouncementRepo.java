package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, UUID> {
}
