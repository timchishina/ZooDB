package com.zoo.domain.model;

import java.time.LocalTime;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
public class FeedingSchedule {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    private LocalTime feedingTime;
    private String foodType;
    private boolean completed;

    public FeedingSchedule() {}

    public FeedingSchedule(UUID id, Animal animal, LocalTime feedingTime, String foodType) {
        this.id = id;
        this.animal = animal;
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
        return animal.getId();
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
