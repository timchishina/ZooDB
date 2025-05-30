package com.zoo.application.port.out;

import com.zoo.domain.model.FeedingSchedule;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FeedingScheduleRepository {
    FeedingSchedule save(FeedingSchedule schedule);
    Optional<FeedingSchedule> findById(UUID id);
    List<FeedingSchedule> findAll();
    void deleteById(UUID id);
    List<FeedingSchedule> findByAnimal_Id(UUID animalId);
    List<FeedingSchedule> findByCompletedFalse();
}
