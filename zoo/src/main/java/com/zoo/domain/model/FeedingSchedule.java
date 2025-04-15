package com.zoo.domain.model;

import java.time.LocalTime;
import java.util.UUID;

public class FeedingSchedule {
    private final UUID id;
    private final UUID animalId;
    private LocalTime feedingTime;
    private String foodType;
    private boolean completed;

    public FeedingSchedule(UUID id, UUID animalId, LocalTime feedingTime, String foodType) {
        this.id = id;
        this.animalId = animalId;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void updateSchedule(LocalTime time, String foodType) {
        this.feedingTime = time;
        this.foodType = foodType;
    }

    public UUID getId() {
        return id;
    }
    
    public UUID getAnimalId() {
        return animalId;
    }
    
    public LocalTime getFeedingTime() {
        return feedingTime;
    }
    
    public String getFoodType() {
        return foodType;
    }
    
    public boolean isCompleted() {
        return completed;
    }
}
