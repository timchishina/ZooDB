package com.zoo.domain.valueobject;

import com.zoo.domain.valueobject.AnimalType;

public enum EnclosureType {
    HERBIVORE,
    CARNIVORE,
    OMNIVORE,
    AQUARIUM,
    AVIARY;

    public boolean isCompatible(AnimalType type) {
        return switch (this) {
            case HERBIVORE -> type == AnimalType.HERBIVORE;
            case CARNIVORE -> type == AnimalType.CARNIVORE;
            case OMNIVORE -> type == AnimalType.OMNIVORE;
            case AQUARIUM -> type == AnimalType.FISH;
            case AVIARY -> type == AnimalType.BIRD;
        };
    }
}
