package com.zoo.application.service;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.FeedingScheduleRepository;
import com.zoo.domain.model.Animal;
import com.zoo.domain.model.FeedingSchedule;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public class FeedingOrganizationService {

    private final FeedingScheduleRepository feedingScheduleRepository;
    private final AnimalRepository animalRepository;

    public FeedingOrganizationService(FeedingScheduleRepository feedingScheduleRepository,
                                      AnimalRepository animalRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
        this.animalRepository = animalRepository;
    }

    public boolean addFeeding(UUID animalId, LocalTime time, String foodType) {
        Optional<Animal> optAnimal = animalRepository.findById(animalId);
        if (optAnimal.isEmpty()) return false;

        Animal animal = optAnimal.get();

        FeedingSchedule schedule = new FeedingSchedule(
                UUID.randomUUID(),
                animal,
                time,
                foodType
        );
        feedingScheduleRepository.save(schedule);
        return true;
    }

    public boolean markFeedingAsCompleted(UUID scheduleId) {
        Optional<FeedingSchedule> optSchedule = feedingScheduleRepository.findById(scheduleId);
        if (optSchedule.isEmpty()) return false;

        FeedingSchedule schedule = optSchedule.get();
        schedule.markAsCompleted();
        feedingScheduleRepository.save(schedule);

        Animal animal = schedule.getAnimal();
        animal.feed(schedule.getFoodType());
        animalRepository.save(animal);

        return true;
    }

    public boolean updateFeeding(UUID scheduleId, LocalTime newTime, String newFoodType) {
        Optional<FeedingSchedule> optSchedule = feedingScheduleRepository.findById(scheduleId);
        if (optSchedule.isEmpty()) return false;

        FeedingSchedule schedule = optSchedule.get();
        schedule.updateSchedule(newTime, newFoodType);
        feedingScheduleRepository.save(schedule);
        return true;
    }
}
