package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.domain.model.Animal;

import java.util.*;

public class InMemoryAnimalRepository implements AnimalRepository {

    private final Map<UUID, Animal> storage = new HashMap<>();

    @Override
    public void save(Animal animal) {
        storage.put(animal.getId(), animal);
    }

    @Override
    public Optional<Animal> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }

    @Override
    public List<Animal> findAll() {
        return new ArrayList<>(storage.values());
    }
}
