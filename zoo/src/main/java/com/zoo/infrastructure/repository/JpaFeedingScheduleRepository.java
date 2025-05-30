package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.FeedingScheduleRepository;
import com.zoo.domain.model.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFeedingScheduleRepository extends FeedingScheduleRepository, JpaRepository<FeedingSchedule, UUID> {

    List<FeedingSchedule> findByAnimal_Id(UUID animalId);
    List<FeedingSchedule> findByCompletedFalse();
}
