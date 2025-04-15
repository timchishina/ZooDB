package com.zoo.application.port.out;

import com.zoo.domain.model.Animal;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface AnimalRepository {
    Optional<Animal> findById(UUID id);
    void save(Animal animal);
    void deleteById(UUID id);
    List<Animal> findAll();
}
