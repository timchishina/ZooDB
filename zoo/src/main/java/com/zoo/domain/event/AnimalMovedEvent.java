package com.zoo.domain.event;

import java.util.UUID;

public class AnimalMovedEvent {
    private final UUID animalId;
    private final UUID fromEnclosureId;
    private final UUID toEnclosureId;

    public AnimalMovedEvent(UUID animalId, UUID fromEnclosureId, UUID toEnclosureId) {
        this.animalId = animalId;
        this.fromEnclosureId = fromEnclosureId;
        this.toEnclosureId = toEnclosureId;
    }

    public UUID getAnimalId() {
        return animalId;
    }
    
    public UUID getFromEnclosureId() {
        return fromEnclosureId;
    }
    
    public UUID getToEnclosureId() {
        return toEnclosureId;
    }
}
