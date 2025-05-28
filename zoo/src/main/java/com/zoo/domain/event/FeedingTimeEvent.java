package com.zoo.domain.event;

import java.util.UUID;
import java.time.LocalTime;

public class FeedingTimeEvent {
    private final UUID animalId;
    private final LocalTime feedingTime;

    public FeedingTimeEvent(UUID animalId, LocalTime feedingTime) {
        this.animalId = animalId;
        this.feedingTime = feedingTime;
    }

    public UUID getAnimalId() {
        return animalId;
    }
    
    public LocalTime getFeedingTime() {
        return feedingTime;
    }
}
 