package com.zoo.domain.model;

import com.zoo.domain.valueobject.EnclosureType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
public class Enclosure {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private EnclosureType type;

    private int maxAnimals;
    private double size;
    private LocalDateTime lastCleanedTime;

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL)
    private Set<Animal> animals;

    public Enclosure(UUID id, EnclosureType type, int maxAnimals, double size) {
        this.id = id;
        this.type = type;
        this.maxAnimals = maxAnimals;
        this.animals = new HashSet<>();
        this.size = size;
        this.lastCleanedTime = null;
    }
    public Enclosure(){}

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

    public LocalDateTime getLastCleanedTime() {
        return lastCleanedTime;
    }
    
    public double getSize() {
        return size;
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
