package com.zoo.domain.valueobject;

public enum AnimalType {
    HERBIVORE,
    CARNIVORE,
    OMNIVORE,
    FISH,
    BIRD;

    
    public boolean isCompatible(AnimalType other) {
        if (this == FISH || this == BIRD) {
            return this == other;
        }
        
        return this == other || this == OMNIVORE || other == OMNIVORE;
    }
}
