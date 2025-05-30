package com.zoo.application.port.out;

import com.zoo.domain.model.Animal;
import com.zoo.domain.valueobject.AnimalType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalRepository {
    Optional<Animal> findById(UUID id);
    Animal save(Animal animal);                
    void deleteById(UUID id);
    List<Animal> findAll();
    List<Animal> findByType(AnimalType type);
    List<Animal> findByHungryTrue();
    Optional<Animal> findByName(String name);
}
