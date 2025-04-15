package com.zoo.domain.model;

import com.zoo.domain.valueobject.EnclosureType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDateTime;

public class Enclosure {
    private final UUID id;
    private final EnclosureType type;
    private final int maxAnimals;
    private final Set<Animal> animals;
    private final double size;
    private LocalDateTime lastCleanedTime;

    public Enclosure(UUID id, EnclosureType type, int maxAnimals, double size) {
        this.id = id;
        this.type = type;
        this.maxAnimals = maxAnimals;
        this.animals = new HashSet<>();
        this.size = size;
        this.lastCleanedTime = null;
    }

    public boolean addAnimal(Animal animal) {
        if (animals.size() < maxAnimals && type.isCompatible(animal.getType())) {
            return animals.add(animal);
        }
        return false;
    }
    
    public void clean(){
        lastCleanedTime = LocalDateTime.now();
        System.out.println("Вольер " + id + " убран в " + lastCleanedTime);
    }

    public boolean removeAnimal(Animal animal) {
        return animals.remove(animal);
    }

    public UUID getId() {
        return id;
    }


    public boolean isFull() {
        return animals.size() >= maxAnimals;
    }

    public int getCurrentCount() {
        return animals.size();
    }

    public EnclosureType getType() {
        return type;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }
}
