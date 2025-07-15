package com.project.RaveRadar.repositories;

import com.project.RaveRadar.models.PersonalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, UUID> {
}
